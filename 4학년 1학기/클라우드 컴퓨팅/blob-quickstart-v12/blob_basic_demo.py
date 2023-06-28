import os
import uuid
from azure.storage.blob import BlobServiceClient, BlobClient, ContainerClient, __version__

try:

    print("Azure Blob Storage v" + __version__ + " - Python quickstart sample")
    connect_str = 'DefaultEndpointsProtocol=https;AccountName=tkdrms0303;AccountKey=MhzcpRQT55UeT00HgKIqGlSuXbhRlgOHwZjUO/HzmUdqAKA3DJSj7Zm7cS6R/Lp1roF9Axv5JEPs+AStXuX22g==;EndpointSuffix=core.windows.net'
    print(connect_str)

    # Create the BlobServiceClient object which will be used to create a container client
    blob_service_client = BlobServiceClient.from_connection_string(connect_str)
    # Create a unique name for the container
    container_name = str(uuid.uuid4())
    # Create the container
    container_client = blob_service_client.create_container(container_name)

    # Create a local directory to hold blob data
    local_path = "./data"
    os.mkdir(local_path)
    # Create a file in the local data directory to upload and download
    local_file_name = str(uuid.uuid4()) + ".txt"
    upload_file_path = os.path.join(local_path, local_file_name)
    # Write text to the file
    file = open(upload_file_path, 'w')
    file.write("Hello, World!")
    file.close()
    # Create a blob client using the local file name as the name for the blob
    blob_client = blob_service_client.get_blob_client(
        container=container_name, blob=local_file_name)
    print("\nUploading to Azure Storage as blob:\n\t" + local_file_name)
    # Upload the created file
    with open(upload_file_path, "rb") as data:
        blob_client.upload_blob(data)

# Quick start code goes here
except Exception as ex:
    print('Exception:')
    print(ex)
