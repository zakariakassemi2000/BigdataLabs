#!/usr/bin/env python3
import sys

# input comes from standard input (STDIN)
for line in sys.stdin:
    line = line.strip()           # remove leading/trailing whitespaces
    words = line.split()          # split line into words

    for word in words:
        # write to standard output (STDOUT)
        print(f"{word}\t1")
