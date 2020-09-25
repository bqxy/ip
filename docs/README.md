# Duke
Duke is a **desktop app for managing any tasks, optimised for use via a Command Line Interface (CLI)**. If you can type fast, Duke can get your task management done faster than traditional GUI apps.


# Quick Start

1. Ensure you have Java ```11``` or above installed in your Computer.
2. Download the latest ```ip.jar```
3. Copy the file to the folder you want to use as the *home folder* for your Task List.
4. In CLI, type ```java <ABSOLUTE_PATH.jar>```*

*For the best experience on Windows, 
1. Change CLI font to ```NSimSun```
2. Type ```chcp 65001```
3. Start the program ```java -Dfile.encoding=UTF-8 -jar <ABSOLUTE_PATH.jar>```

# Features
* List Tasks: ```list```
* To-Do Task: ```todo```
* Event Task: ```event```
* Deadline Task: ```deadline```
* Done Task: ```done```
* Delete Task: ```delete```
* Find Tasks: ```find```
* End Program: ```bye```

### List of Tasks: ```list```
Shows the list of tasks stored in Duke.  
Format: ```list```

### To-Do Task: ```todo```
Stores a new To-Do task.  
Format: ```todo <DETAILS>```

### Event Task: ```event```
Stores a new Event task.  
Format: ```event <DETAILS> /at <DATE_AND_OR_TIME>```  
If date and/or time is entered in this format: ```YYYY-MM-DD TTTT```, Duke will recognise and output it in the following format ```Month Day Year Time```  
For example, if user inputs ```2020-1-1 1800```, the output by Duke is ```Jan 1 2020 1800```

### Deadline Task: ```deadline```
Stores a new Deadline task.  
Format: ```deadline <DETAILS> /by <DATE_AND_OR_TIME>```  
If date and/or time is entered in this format: ```YYYY-MM-DD TTTT```, Duke will recognise and output it in the following format ```Month Day Year Time```  
For example, if user inputs ```2020-1-1 1800```, the output by Duke is ```Jan 1 2020 1800```

### Done Task: ```done```
Marks a task in the list as done.  
Format: ```done <TASK_NUMBER>```

### Delete Task: ```delete```
Deletes a task in the list.  
Format: ```delete <TASK_NUMBER>```

### Find Task: ```find```
Find task using search words.  
Format: ```find <SEARCH_WORDS>```

### End Program: ```bye```
Ends program.  
Format: ```bye```