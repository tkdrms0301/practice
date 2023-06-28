import logging
import json
import random

random.seed(10)

def main(phoneNumber, message):
    code = random.randint(1000, 10000)
    payload = {
        "body": f"Your verification code is {code}",
        "to": phoneNumber
    }
    logging.warning(payload)
    message.set(json.dumps(payload))
    code_str = str(code)
    logging.warning(code_str)
    return code_str

