import time
from typing import Any, Dict
import logging
import json

#src ="123456786" ## true case
#src ="876543216"true
# account validity check
# all numbers, len 8, last int = SUM first 7 int MOD 10 
def checkAccount(src) :
    if not src.isnumeric() :
        return False

    if( len(src) != 9 ) :
        return False
    else :
        arr = list(src)
        arr = arr[:-1]
        intarr = [int(x) for x in arr]
        sa = sum(intarr)
        rem = sa % 10
        if( rem != int(src[-1]) ) :
            return False
        else :
        #print("OK")
            return True
        
def main(transfer: Dict[str, Any]) -> Dict[str, Any]:
    logging.info(f"**validate-input : {transfer}")
    if not transfer:
        raise Exception('The funds transfer data is mandatory')
    if not transfer.get('sourceAccount'):
        raise Exception('The Source Account is mandatory')
    # account ex = {"id": "123456786", "bankId": "001" }
    src = transfer.get('sourceAccount') ##account check 
    resp = json.loads(json.dumps(src))
    if( not checkAccount(resp['id']) ) :
        raise Exception('The Source Account is invalid')
    if not transfer.get('targetAccount'):
        raise Exception('The Target Account is mandatory')
    target = transfer.get('targetAccount')  ##account check 
    resp = json.loads(json.dumps(target))
    if( not checkAccount(resp['id']) ) :
        raise Exception('The Target Account is invalid')
    return transfer

