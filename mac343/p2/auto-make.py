#!/usr/bin/env python3
import subprocess
import time

while 42:
    subprocess.call("pdflatex p2.tex", shell=True, timeout=5)
    time.sleep(5)
