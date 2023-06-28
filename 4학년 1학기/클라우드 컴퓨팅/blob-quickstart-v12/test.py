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

    print("\nListing blobs...")
    # List the blobs in the container
    blob_list = container_client.list_blobs()
    for blob in blob_list:
        print("\t" + blob.name)

# Quick start code goes here
except Exception as ex:
    print('Exception:')
    print(ex)
