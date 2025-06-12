import websocket
import json
import time
import uuid
from datetime import datetime, timedelta

class McpSimulationClient:
    def __init__(self, server_url="ws://localhost:8080/mcp-websocket"):
        self.server_url = server_url
        self.ws = None

    def connect(self):
        self.ws = websocket.WebSocketApp(
            self.server_url,
            on_message=self.on_message,
            on_error=self.on_error,
            on_close=self.on_close,
            on_open=self.on_open
        )

    def on_message(self, ws, message):
        print(f"Received: {message}")
        response = json.loads(message)
        if response.get("type") == "RESPONSE":
            self.handle_response(response)

    def on_error(self, ws, error):
        print(f"Error: {error}")

    def on_close(self, ws, close_status_code, close_msg):
        print("Connection closed")

    def on_open(self, ws):
        print("Connection established")
        self.send_sample_queries()

    def send_message(self, message_type, content, context=None):
        message = {
            "messageId": str(uuid.uuid4()),
            "type": message_type,
            "content": content,
            "context": context or {},
            "timestamp": datetime.now().isoformat(),
            "status": "PENDING",
            "metadata": {}
        }
        self.ws.send(json.dumps(message))
        return message["messageId"]

    def send_sample_queries(self):
        # Sample query for account balance
        self.send_message(
            "QUERY",
            "Get account balance for customer",
            {
                "queryType": "ACCOUNT_BALANCE",
                "customerId": "CUST001"
            }
        )

        # Sample query for transaction history
        self.send_message(
            "QUERY",
            "Get last month's transactions",
            {
                "queryType": "TRANSACTION_HISTORY",
                "customerId": "CUST001",
                "startDate": (datetime.now() - timedelta(days=30)).isoformat(),
                "endDate": datetime.now().isoformat()
            }
        )

        # Sample query for spending analysis
        self.send_message(
            "QUERY",
            "Analyze spending by category",
            {
                "queryType": "SPENDING_ANALYSIS",
                "customerId": "CUST001",
                "period": "LAST_MONTH"
            }
        )

    def handle_response(self, response):
        print("\nProcessing response:")
        print(f"Message ID: {response.get('messageId')}")
        print(f"Content: {response.get('content')}")
        if "metadata" in response:
            print("Metadata:")
            for key, value in response["metadata"].items():
                print(f"  {key}: {value}")

def main():
    client = McpSimulationClient()
    client.connect()
    
    # Keep the connection alive
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print("\nShutting down client...")

if __name__ == "__main__":
    main() 