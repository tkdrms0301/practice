import logging
import azure.functions as func
import azure.durable_functions as df

def orchestrator_function(context: df.DurableOrchestrationContext):

    root_directory: str = context.get_input()
    ##print(root_directory)
    if not root_directory:
        raise Exception("A directory path is required as input")

    files = yield context.call_activity("E2_GetFileList", root_directory)
    logging.info(files)
    tasks = []
    for file in files:
        logging.info(file)
        tasks.append(context.call_activity("E2_CopyFileToBlob", file))

    results = yield context.task_all(tasks)
    total_bytes = sum(results)
    return total_bytes

main = df.Orchestrator.create(orchestrator_function)
