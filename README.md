# Relative-Frequency
MapReduce code to find relative frequency of words in 100,000 Wikipedia document file

# How to run:
* Create .jar file of the source code
* Save this document on your local machine "https://web.njit.edu/~chasewu/Courses/Spring2016/CS698BigData/HW/100KWikiText.txt"
* Copy this saved file to HDFS using command:
```
hadoop fs -put WikiData.txt WikiData
```
* Run RelativeFrequency.jar on WikiData using command:
```
hadoop jar RelativeFrequency.jar WikiData RelativeFrequencyOutput
```
