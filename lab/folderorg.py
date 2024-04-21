import os
import datetime
import platform

def get_creation_time(path):
    """
    Get the creation time of the file or directory, handling different platforms.
    """
    if platform.system() == 'Windows':
        return os.path.getctime(path)
    else:
        stat = os.stat(path)
        try:
            return stat.st_birthtime
        except AttributeError:
            # We're probably on Linux. No easy way to get creation dates here,
            # so we'll settle for when the file was last modified.
            return stat.st_mtime

def prepend_index_based_on_creation(directory):
    """
    Prepend the index of each folder sorted by creation date to the folder names.
    """
    folders = []
    # Collect all folders and their creation times
    for item in os.listdir(directory):
        item_path = os.path.join(directory, item)
        if os.path.isdir(item_path):
            creation_time = get_creation_time(item_path)
            folders.append((item_path, creation_time))
    
    # Sort folders by creation time
    folders.sort(key=lambda x: x[1])
    
    # Rename folders by prepending index
    for index, (folder_path, _) in enumerate(folders):
        folder_name = os.path.basename(folder_path)
        new_folder_name = f"{index}_{folder_name}"
        new_folder_path = os.path.join(directory, new_folder_name)
        os.rename(folder_path, new_folder_path)
        print(f"Renamed '{folder_name}' to '{new_folder_name}'")

# Usage
directory = '.'
prepend_index_based_on_creation(directory)
