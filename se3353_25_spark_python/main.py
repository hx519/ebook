# """SimpleApp.py"""
# from pyspark.sql import SparkSession
# print("Spark session initialized.")
#
# logFile = "D:/spark/spark-3.5.0-bin-hadoop3/README.md"  # Should be some file on your system
# spark = SparkSession.builder.appName("SimpleApp").getOrCreate()
# print("Spark session initialized.")
# logData = spark.read.text(logFile).cache()
# print("Spark session initialized.")
# numAs = logData.filter(logData.value.contains('a')).count()
# numBs = logData.filter(logData.value.contains('b')).count()
#
# print("Lines with a: %i, lines with b: %i" % (numAs, numBs))
#
# spark.stop()


from pyspark import SparkConf, SparkContext
import os
import shutil

def delete_directory(directory_path):
    try:
        # 使用 shutil.rmtree 删除目录及其内容
        shutil.rmtree(directory_path)
        print(f"Directory {directory_path} deleted successfully.")
    except OSError as e:
        print(f"Error deleting directory {directory_path}: {e}")

conf = SparkConf().setAppName("SimpleApp")
sc = SparkContext(conf=conf)

# 读取输入目录中的所有文件
input_directory = "D:\\bookstore\\demo\\se3353_25_spark_python\\books"
text_files = sc.wholeTextFiles(input_directory)

# 定义关键词
keywords = ["classic", "fiction", "science", "adventure"]

# 进行映射和过滤操作
counts = (
    text_files.flatMap(lambda kv: kv[1].lower().split())
    .filter(lambda word: any(keyword in word for keyword in keywords))
    .map(lambda word: (word.strip(".,?!"), 1))
    .reduceByKey(lambda a, b: a + b)
)

# 合并相同类别的项
merged_counts = counts.map(lambda x: (x[0].split(',')[0], x[1])).reduceByKey(lambda a, b: a + b)

# 合并到一个分区
merged_counts = merged_counts.coalesce(1)

# 保存结果到输出文件
output_directory = "D:\\bookstore\\demo\\se3353_25_spark_python\\output"
if os.path.exists(output_directory):
    delete_directory(output_directory)

merged_counts.saveAsTextFile(output_directory)

# 停止 SparkContext
sc.stop()
