# projectRH

###### Requirements
JDK 11 and above

## Implemention in Java

```
"mini-grep"
-----------

Usage:

    ./mini-grep [-q] -e PATTERN [FILE...]

`mini-grep` goes through every argument in FILE and prints the whole
line in which PATTERN is found. By default `mini-grep` also outputs
the line number of each printed line.

- PATTERN has to be a valid regex
- FILE can be zero or more arguments. If zero args are given,
  `mini-grep` will parse entries from the standard input.
- If given, the `-q` options only outputs lines but omits the matching
  line numbers.



"mini-ls"
---------

Usage

    ./mini-ls [-r] [FILE...]

`mini-ls` lists information about the paths given in FILE. The
information required are: Owner, Permission, Modified Time.

- FILE can be zero or more arguments. If zero args are given,
  `mini-ls` will list information about the current directory.
- If given, the `-r` option will make `mini-ls` run recursively on any
  directory it comes across.


"mini-df"
---------

Usage:

    ./mini-df [-h] [PATH...]

`mini-df` outputs the file system disk space usage of each entry in
PATH. The information required is: Total Space, Free Space, Used
Space. The result should be in Bytes.

- PATH can be zero or more arguments. IF zero args are given,
  `mini-df` will list the disk space usage of the current directory.
- If given `-h` will output the result in human-readable format.
```
