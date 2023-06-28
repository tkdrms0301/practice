import logging
import os
from os.path import dirname
from typing import List


def main(rootDirectory: str) -> List[str]:
    all_file_paths = []

    # We walk the file system
    for path, _, files in os.walk(rootDirectory):
        # For each file, we add their full-path to the list
        for name in files:
            file_path = os.path.join(path, name)
            all_file_paths.append(file_path)

    logging.info(all_file_paths)
    return all_file_paths
