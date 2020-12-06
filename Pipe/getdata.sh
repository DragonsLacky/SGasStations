#!/bin/bash

reg='^<node.*\">$'

while read data_line; do
  if [[ $data_line =~ $reg ]]; then
    node=$(echo $data_line | sed "s/<node\s/\{_/g" | sed "s/\=/:/g" | sed "s/>//g" | sed "s/\s/,/g")
    read data_line
    while [[ $data_line != "</node>" ]]; do
      key=$(echo $data_line | grep -o -P '(?<=k\=\").*(?=\"\s)')
      key=$(echo "\"$key\"")
      val=$(echo $data_line | grep -o -P '(?<=v\=).*(?=/>)')
      if [[ $val == "\"yes\"" ]]; then
        val="true"
      elif [[ $val == "\"no\"" ]]; then
        val="false"
      fi
      tag=$(echo "$key:$val")
      node+=$(echo ",$tag")
      read data_line
    done
    node+="}"
    if [[ $node == *"fuel"* ]]; then
      echo $node
    fi
  fi
done <"${1:-/dev/stdin}"
