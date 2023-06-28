import re
import azure.durable_functions as df
def orchestrator_function(context: df.DurableOrchestrationContext):
    output = []
    request_body = context.get_input()
    try:
        data = yield context.call_activity('get-funds-transfer-data', request_body)
        output.append('Get funds transfer data: DONE')
        yield context.call_activity('validate-input', data)
        output.append('Validate input: DONE')
        yield context.call_activity('handle-funds-transfer', data)
        output.append('Handle funds transfer: DONE')
        return output
    except Exception as e:
        output.append(e)
        raise Exception(output) 
main = df.Orchestrator.create(orchestrator_function)
