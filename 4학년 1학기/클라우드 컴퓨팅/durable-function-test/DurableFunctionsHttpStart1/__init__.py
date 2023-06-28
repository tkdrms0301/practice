# This function an HTTP starter function for Durable Functions.
# Before running this sample, please:
# - create a Durable orchestration function
# - create a Durable activity function (default name is "Hello")
# - add azure-functions-durable to requirements.txt
# - run pip install -r requirements.txt

import logging
import azure.functions as func
import azure.durable_functions as df


async def main(req: func.HttpRequest, starter: str) -> func.HttpResponse:
    client = df.DurableOrchestrationClient(starter)
    instance_id = await client.start_new(req.route_params["functionName"], None, None)

    logging.info(f"Started orchestration with ID = '{instance_id}'.")

    timeout_in_milliseconds = get_time_in_seconds(req, 2)  # 2sec
    timeout_in_milliseconds = (
        timeout_in_milliseconds if timeout_in_milliseconds != None else 30000
    )
    retry_interval_in_milliseconds = get_time_in_seconds(req, 0.5)  # 0.5sec
    retry_interval_in_milliseconds = (
        retry_interval_in_milliseconds
        if retry_interval_in_milliseconds != None
        else 1000
    )

    mess = await client.wait_for_completion_or_create_check_status_response(
        req, instance_id, timeout_in_milliseconds, retry_interval_in_milliseconds
    )
    return mess


def get_time_in_seconds(req: func.HttpRequest, query_parameter_name: str):
    query_value = req.params.get(query_parameter_name)
    return query_value if query_value != None else 1000
