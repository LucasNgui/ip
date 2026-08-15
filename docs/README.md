# Atom User Guide

// Product screenshot goes here

Atom is a chatbot designed to help keep track of tasks.

## Adding todos

Add a task without a start or end time.

Example: `todo borrow book`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've added this task:
[D][ ] borrow book
You now have 1 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Adding deadlines

Add a task with only an end time.

Example: `deadline return book /by Sunday`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've added this task:
[D][ ] return book (by: Sunday)
You now have 2 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Adding events

Add a task with both a start time and an end time.

Example: `event project meeting /from Mon 2pm /to 4pm`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've added this task:
[D][ ] project meeting (from: Mon 2pm to: 4pm)
You now have 3 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Listing tasks

List all tasks that have been added so far.

Example: `list`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 

## Marking and unmarking tasks

Mark a task as done or undone.

Example: `mark 1`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
1. [T][X] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 

`unmark 1`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Deleting tasks

Delete a task from the list.

Example: `delete 2`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've removed this task:
[D][ ] return book (by: Sunday)
You now have 2 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 
