# ThreadQuickRank
###用多线程实现快排

GenerateArray用来生成测试用例

Qsort普通快排

QsortPlus没用的东西

RankAlgorithm主程序

RankAlgorithmThread1没用的东西

RankAlgorithmThread2多线程快排程序

---
设计思路为：将数组分区块后，如果该区块大于MAX_BLOCK(5000)则建立线程来计算，如果小于，则直接使用快排函数Qsort计算。

线程使用forkjoin线程池，最多同时运行4个线程。

代码尚未整理，仍有许多不足之处（例如多余的声明和继承，多余的函数等等）我还会继续整理。

本人觉得该代码仍有可以改进之处，欢迎探讨。
