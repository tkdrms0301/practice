import time
from typing import Any, Dict
import logging
import json
# Using `transfer` as the parameter name because `data` causes a
# `Microsoft.Azure.WebJobs.Host: Can't bind parameter 'data' to type 'System.String'.` error.
# https://github.com/Azure/Azure-Functions/issues/1281 for reference.
def main(transfer: Dict[str, Any]) -> Dict[str, Any]:
    time.sleep(2)
    logging.info("Transfer")
    logging.info(transfer)
    # The actual funds transfer logic, such as moving money from the source to the target account
    # should be added here.
    if not transfer.get('amount'):
        raise Exception('The Amount is mandatory')
#
    src = transfer.get('amount') ## amount check
    logging.info(src) 


    if( int(src) < 100) :
        raise Exception('The Amount is invalid')
    return transfer
