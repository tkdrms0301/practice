import logging

import azure.functions as func
import re


def remove_html_tags(raw_html):
    cleanr = re.compile("<.*?>")
    cleantext = re.sub(cleanr, "", raw_html)
    return cleantext


def main(req: func.HttpRequest) -> func.HttpResponse:
    logging.info("Python HTTP trigger function processed a request.")
    logging.info(req.get_body())

    # message = req.get_body().decode()
    mess = req.get_body().decode()
    message = str(mess)
    message = message.replace("\\r\\n", " ")

    ####
    out = remove_html_tags(message)
    ###

    return func.HttpResponse(
        str(len(out)), mimetype="application/x-www-form-urlencoded"
    )
