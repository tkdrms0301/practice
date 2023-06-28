import time
from typing import Any, Dict
import logging
def main(body: Dict[str, Any]) -> Dict[str, Any]:
    logging.info(f"get-funds-transfer-data: {body['data']}")
    return body['data'] if body and body.get('data') else {}
