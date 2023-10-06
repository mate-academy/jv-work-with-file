# jv-read-from-file
You are given a file that contains different words as well as punctuation.

You need to filter out only the words starting with `w`, and remove any punctuation if necessary.

The result should be returned as a naturally sorted array.
All words should be lower-case.

If the file does not contain the necessary words, return an empty array.

Examples:
```
"Width world Wide web"
Result: ["web", "wide", "width", "world"]

"WWW? Four-bedroom farmhouse in the countryside. Wave! All of the four double bedrooms are en suite."
Result: ["wave", "www"]
```

Hint: try to split Strings with a regular expression that includes whitespace and punctuation characters.
Here is a good [article](https://stackoverflow.com/questions/13225175/java-string-split-with-a-regex).
#### [Try to avoid these common mistakes, while solving task](https://mate-academy.github.io/jv-program-common-mistakes/java-core/builder-file/read-from-file.html)
