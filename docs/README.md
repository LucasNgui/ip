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

Example: `deadline return book /by 2026-04-30`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've added this task:
[D][ ] return book (by: Apr 30 2026)
You now have 2 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Adding events

Add a task with both a start time and an end time.

Example: `event project meeting /from 2026-04-14 /to 2026-04-15`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've added this task:
[D][ ] project meeting (from: Apr 14 2026 to: Apr 15 2026)
You now have 3 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Listing tasks

List all tasks that have been added so far.

Example: `list`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
1. [T][ ] borrow book
2. [D][ ] return book (by: Apr 30 2026)
3. [E][ ] project meeting (from: Apr 14 2026 to: Apr 15 2026)
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 

## Marking and unmarking tasks

Mark a task as done or undone.

Example: `mark 1`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Awesome! Marking this task as done:
[T][X] borrow book
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 

`unmark 1`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Ok. Marking this task as undone:
[T][ ] borrow book
~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Deleting tasks

Delete a task from the list.

Example: `delete 2`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
Alright! I've removed this task:
[D][ ] return book (by: Apr 30 2026)
You now have 2 tasks in the list.
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 

## Finding tasks

Find a task using a keyword.

Example: `find book`

```
~~~~~~~~~~~~~~~~~~~~~~~~~
I've found these matching tasks in your list:
1. [T][ ] borrow book
~~~~~~~~~~~~~~~~~~~~~~~~~
``` 
