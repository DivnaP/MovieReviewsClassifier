
# 1. About the project
The aim of this project is to experiment with different machine learning algorithms to predict the sentiment of movie reviews. For start we collect movie reviews from database on the website [www.cs.cornell.edu](http://www.cs.cornell.edu/people/pabo/movie-review-data) and use that data to create dataset file and predict sentiment of specific movie review.
The project uses five different types of prediction models : <br>
- Logistic Regression
- Support Vector Machine
- Naive Bayes Multinomial
- J48 Decision tree
- Sequential Minimal Optimization<br>

Results of the five prediction models are compared in order to find out which method is more suitable for this type of prediction. 
Beside those five models, aplication provide analyzing of words ih movie reviews using [SentiWordNet](http://sentiwordnet.isti.cnr.it/) lexical resource. Also user have ability to use  [HP IDOL OnDemand](https://www.idolondemand.com/developer/apis/analyzesentiment#overview) Web service which analyzes text and return the sentiment as positive, negative or neutral. It contains a dictionary of positive and negative words of different types, and defines patterns that describe how to combine these words to form positive and negative phrases.


# 2. Machine learning algorithms used in project

*Logistic Regression* is one of the best probabilistic classifiers, measured in both log loss and first-best classification accuracy across a number of tasks. The dimensions of the input vectors being classified are called "features" and there is no restriction against them being correlated. Logistic regression can be binomial, ordinal or multinomial. Binomial or binary logistic regression deals with situations in which the observed outcome for a dependent variable can have only two possible types.[1] Multinomial logistic regression deals with situations where the outcome can have three or more possible types.[1]

*Multinomial Naive Bayes* is a specialized version of Naive Bayes that is designed more for text documents. Whereas simple naive Bayes would model a document as the presence and absence of particular words, multinomial naive bayes explicitly models the word counts and adjusts the underlying calculations to deal with in. 

*Support vector machine* is a supervised learning model, very similar to linear regression, that analyzes data, recognizes patters and uses these results for predictions. The advantage of Support Vector Machines is that they can make use of certain kernels in order to transform the problem, such that we can apply linear classification techniques to non-linear data.

*Sequential minimal optimization (SMO)* is an algorithm for solving the quadratic programming problem that arises during the training of support vector machines.[2] 

*J48 Decision tree* is a predictive machine-learning model that decides the target value (dependent variable) of a new sample based on various attribute values of the available data.[3]

*SENTIWORDNET 3.0* is a lexical resource explicitly devised for supporting sentiment classification and opinion
mining applications. SentiWordNet is the result of the automatic annotationof all the synsets of WORDNET according to the notions of “positivity”, “negativity”, and “neutrality”. Each synset s is associated to three numerical scores P os(s), Neg(s), and Obj(s) which indicate how positive, negative, and “objective” (i.e., neutral) the terms contained in the synset are. Different senses of the same term may thus have different opinion-related propertie.
# 3. 
## Creating dataset

Data used in this project is collected from the website [www.www.cs.cornell.edu](http://www.cs.cornell.edu/people/pabo/movie-review-data). For the purpose of this project we used  [polarity_dataset v2.0](http://www.cs.cornell.edu/People/pabo/movie-review-data/review_polarity.tar.gz). All data from this database are sorted in two folders pos and neg, which represent positive and negative reviews. All reviews from .txt files from these two folders are programmatically stored in file ["data/movieReviews.arff"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/movieReviews.arff) with the help of class ["InsertReviewsToARFF.java"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/src/rs/fon/is/movieClassification/util/InsertReviewsToARFF.java). Some of movie reviews are manually added from [www.metacritic.com](http://www.metacritic.com/) and then, in total, 2158 movie reviews collected.

## Train dataset and testing
User can perform training on dataset selecting one of the five classifiers. After that user can see results of training on screen and he can load or enter review for classification.
![Alt text](/images/formTraining1.jpg?raw=true "Chooseing classifier")
![Alt text](/images/formTraining2.jpg?raw=true "Results of training")<br>
When algorithm learn we applying StringToWordVector with filter stopwords from file ["data/stopWords2.txt"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/stopWords2.txt) which represent list of words that we won't to analyze because there are irelevant for our classification.
![Alt text](/images/stopWordsCode.jpg?raw=true "Filter Stopwords")<br>
When training is done, ["data/model.txt"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/model.txt) file is created. This is model which classification will be based on. User have choice to classify movie review with training model or to use SentiWordNet or maybe to call Web service IDOLOnDemand.
If we use IDOLOnDemand we create HTTP get request with user review. We receive answer in format of JSON object, which will be parsed and represented to user:
![Alt text](/images/IDOLOnDemandAnswer1.jpg?raw=true "JSONAnswer") ![Alt text](/images/IDOLOnDemandAnswer2.jpg?raw=true "JSONAnswer parth2")<br>

If we use SWN, result of sentiment analysis will be shown in next format:
![Alt text](/images/SWNResult.jpg?raw=true "JSONAnswer")

# 4. Technical realization

The application is written in Java programming language, with help of next libraries:<br>
- [Weka library](http://www.cs.waikato.ac.nz/ml/weka/)- Weka is a popular suite of machine learning software written in Java, developed at the University of Waikato, New Zealand. All algorithms that software provide can be used directly from code by importing *weka.jar* file. Weka contains tools for data preprocessing, classification, regression, clustering and visualization.

In this project five Weka classes were used: *NaiveBayesMultinomial*, *J48*, *LibSVM*, *Logistic*, *SMO*.

- [LIBSVM](https://www.csie.ntu.edu.tw/~cjlin/libsvm/) is an integrated software for support vector classification. Weka and LibSVM are two efficient software tools for building SVM classifiers. Each one of these two tools has its points of strength and weakness. Weka has a GUI and produces many useful statistics (e.g. confusion matrix, precision, recall, F-measure, and ROC scores). LibSVM runs much faster than Weka SMO and supports several SVM methods (e.g. One-class SVM, nu-SVM, and R-SVM). Weka LibSVM (WLSVM) combines the merits of the two tools. WLSVM can be viewed as an implementation of the LibSVM running under Weka environment.<br>

- [GSON](http://mvnrepository.com/artifact/com.google.code.gson/gson/2.3.1) is a Java API, developed by Google, used to convert between Java objects and JSON objects.

- [ITEXT](https://github.com/itext/itextpdf) is an open source library that allows you to create and manipulate PDF documents. With this library it's enabled users to see report of the sentiment analzye of movie review in PDF format by clicking on the printer icon.

- [Apache HTTPComponents](https://hc.apache.org/) it's used for API calls to Web Service IDOLOnDemand.The Apache HttpComponents™ project is responsible for creating and maintaining a toolset of low level Java components focused on HTTP and associated protocols. In this project we used httpclient-4.5. jar. httpcore-4.4.1.jar, appache-httpcomponents-httpclient.jar, also [commons-logging-1.2.jar](https://commons.apache.org/proper/commons-logging/download_logging.cgi).


# 5. Analysis


![Alt text](/images/table1.jpg?raw=true "Classification results")

We have results for 5 different algorithms which are performed on dataset movieReviews.arff. 

The column *Precision* represent proportion of instances that are truly of a class divided by the total instances classified as that class, column
*Recall* represent proportion of instances classified as a given class divided by the actual total in that class. And we have a *F-Measure* whitch is a combined measure for precision and recall calculated as 2 * Precision * Recall / (Precision + Recall). Also we have column of correctly classified instances of total 2158. <br>

As we see best precision have Sequential minimal optimization algorithm, which also has best result in recall. After him, best result provides Naive Bayes Multinomial. We have pretty good results but in some cases while testing, application was unable to correctly classify obvious sentiment of movie review. When we use another methods for analyzing sentiment of movie review like SentiWordNet and IDOLONDemand service we have different results.

![Alt text](/images/ClassificationResult1.jpg?raw=true "Classification result when using classification based on training with SMO ")
![Alt text](/images/ClassificationResult2.jpg?raw=true "Classification result when using SentiWordNet")
![Alt text](/images/ClassificationResult3.jpg?raw=true "Classification result when using HPIDOLOnDemand")


# 6. References

[1] Wikipedia, "Logistic_regression", link:https://en.wikipedia.org/wiki/Logistic_regression, last access: 08.10.2015. <br>
[2] Wikipedia, "Sequential_minimal_optimization", link:https://en.wikipedia.org/wiki/Sequential_minimal_optimization, last access: 08.10.2015.<br>
[3] UniversityOfMinnesotaDuluth, "Chapter 5", link:http://www.d.umn.edu/~padhy005/Chapter5.html,last access: 08.10.2015. <br>
