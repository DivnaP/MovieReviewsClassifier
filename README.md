
# 1. About the project
The aim of this project is to experiment with different machine learning algorithms to predict the sentiment of movie reviews. The prediction was performed on the movie review dataset available on the website [www.cs.cornell.edu](http://www.cs.cornell.edu/people/pabo/movie-review-data).
The project uses five different types of prediction models:
- Logistic Regression
- Support Vector Machine
- Naive Bayes Multinomial
- J48 Decision tree
- Sequential Minimal Optimization<br>

Results of the five prediction models are compared in order to find out which method is more suitable for this type of prediction. 
Beside those five models, application provides analysis of words in movie reviews using [SentiWordNet](http://sentiwordnet.isti.cnr.it/) lexical resource. Also, an user has an option to compare the results of predicion to the result returned by the [HP IDOL OnDemand](https://www.idolondemand.com/developer/apis/analyzesentiment#overview), that is the Sentiment Analysis API which analyzes text to return the sentiment as positive, negative or neutral. It contains a dictionary of positive and negative words of different types, and defines patterns that describe how to combine these words to form positive and negative phrases.


# 2. Machine learning algorithms, resources and services used

*Logistic Regression* is one of the best probabilistic classifiers, measured in both log loss and first-best classification accuracy across a number of tasks. The dimensions of the input vectors being classified are called "features" and there is no restriction against them being correlated. Logistic regression can be binomial, ordinal or multinomial. Binomial or binary logistic regression deals with situations in which the observed outcome for a dependent variable can have only two possible types.[1] Multinomial logistic regression deals with situations where the outcome can have three or more possible types.[1]

*Multinomial Naive Bayes* is a specialized version of Naive Bayes that is designed more for text documents. Whereas simple naive Bayes would model a document as the presence and absence of particular words, multinomial Naive Bayes explicitly models the word counts and adjusts the underlying calculations to deal with it. 

*Support vector machine* is a supervised learning model, very similar to linear regression, that analyzes data, recognizes patters and uses these results for predictions. The advantage of Support Vector Machines is that they can make use of certain kernels in order to transform the problem, such that we can apply linear classification techniques to non-linear data.


*J48 Decision tree* is a predictive machine-learning model that decides the target value (dependent variable) of a new sample based on various attribute values of the available data.[2]

*SENTIWORDNET 3.0* is a lexical resource explicitly devised for supporting sentiment classification and opinion mining applications. SentiWordNet is the result of the automatic annotation of all the synsets of WORDNET according to the notions of “positivity”, “negativity”, and “neutrality”. Each synset s is associated to three numerical scores P os(s), Neg(s), and Obj(s) which indicate how positive, negative, and “objective” (i.e., neutral) the terms contained in the synset are. Different senses of the same term may thus have different opinion-related propertie.

*HP IDOL OnDemand* is Web service which analyzes text and return the sentiment as positive, negative or neutral. It contains a dictionary of positive and negative words of different types, and defines patterns that describe how to combine these words to form positive and negative phrases.

# 3. Solution
## Movie review dataset
<<<<<<< HEAD

Dataset used in this project is downloaded from the website [www.cs.cornell.edu](http://www.cs.cornell.edu/people/pabo/movie-review-data), specifically [polarity_dataset v2.0](http://www.cs.cornell.edu/People/pabo/movie-review-data/review_polarity.tar.gz). All data from this dataset are sorted in two folders *pos* and *neg*, which represent positive and negative reviews. In total, there are 1000 positive and 1000 negative reviews. Data from this file is then converted to the appropriate ARFF file stored at ["data/movieReviews.arff"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/movieReviews.arff). Several movie reviews are manually added from [www.metacritic.com](http://www.metacritic.com/) and then, in total, 2158 movie reviews are collected.
Test dataset is also downloaded from the website [www.cs.cornell.edu](http://www.cs.cornell.edu/people/pabo/movie-review-data), specifically [polarity_dataset v1.1](http://www.cs.cornell.edu/people/pabo/movie-review-data/mix20_rand700_tokens_0211.tar.gz) and converted to ARFF file stored at ["data/movieReviewsTestDataset.arff"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/movieReviewsTestDataset.arff)

=======

Dataset used in this project is downloaded from the website [www.cs.cornell.edu](http://www.cs.cornell.edu/people/pabo/movie-review-data), specifically [polarity_dataset v2.0](http://www.cs.cornell.edu/People/pabo/movie-review-data/review_polarity.tar.gz). All data from this dataset are sorted in two folders *pos* and *neg*, which represent positive and negative reviews. In total, there are 1000 positive and 1000 negative reviews. Data from this file is then converted to the appropriate ARFF file stored at ["data/movieReviews.arff"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/movieReviews.arff). Several movie reviews are manually added from [www.metacritic.com](http://www.metacritic.com/) and then, in total, 2158 movie reviews are collected.
>>>>>>> origin/master
![Alt text](/images/movieReviews.jpg?raw=true "movieReviews.arff")
## Training and testing the classifiers
User can perform training on dataset by using one of the five classifiers. Before the algorithm starts the training, filter StringToWordVector is applied which uses stopwords from the file ["data/stopWords2.txt"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/stopWords2.txt) to exclude words that are irrelevant for the classification process.

<<<<<<< HEAD
=======
## Training and testing the classifiers
User can perform training on dataset by using one of the five classifiers. Before the algorithm starts the training, filter StringToWordVector is applied which uses stopwords from the file ["data/stopWords2.txt"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/stopWords2.txt) to exclude words that are irrelevant for the classification process.

>>>>>>> origin/master
![Alt text](/images/stopWordsCode.jpg?raw=true "Filter Stopwords")<br>

After the training has been completed, ["data/model.txt"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/model.txt) file is created that will be used later for movie reviews classification. The results are displayed to the user and he/she can load or enter unclassified review for classification.<br>
![Alt text](/images/formTraining1.jpg?raw=true "Choosing classifier")
![Alt text](/images/formTraining2.jpg?raw=true "Results of training")

Besides having an option to classify movie review based on the training model training model, user can also choose to use IDOLOnDemand or SentiWordNet.

If IDOLOnDemand service is chosen, application will issue a HTTP GET request containing the user review towards the service's API. <br>
![Alt text](/images/getRequest.jpg?raw=true "HTTP get request") <br>

The response recieved is in the JSON format, which will be parsed and presented to the user:<br>
![Alt text](/images/IDOLOnDemandAnswer1.jpg?raw=true "JSONAnswer")<br>
![Alt text](/images/IDOLOnDemandAnswer2.jpg?raw=true "JSONAnswer parth2")<br>

If SentiWordNet is used, the result of the sentiment analysis will be displayed in the following format:<br>
![Alt text](/images/SWNResult.jpg?raw=true "JSONAnswer")<br>

# 4. Technical realization

The application is written in Java programming language, with use of the following libraries :<br>
- [Weka](http://www.cs.waikato.ac.nz/ml/weka/) is a popular suite of machine learning software written in Java, developed at the University of Waikato, New Zealand. All algorithms that software provide can be used directly from code by importing *weka.jar* file. Weka contains tools for data preprocessing, classification, regression, clustering and visualization.

In this project five Weka classes were used: *NaiveBayesMultinomial*, *J48*, *LibSVM*, *Logistic*, *SMO*.

- [LIBSVM](https://www.csie.ntu.edu.tw/~cjlin/libsvm/) is an integrated software for support vector classification. Weka and LibSVM are two efficient software tools for building SVM classifiers. Each one of these two tools has its points of strength and weakness. Weka has a GUI and produces many useful statistics (e.g. confusion matrix, precision, recall, F-measure, and ROC scores). LibSVM runs much faster than Weka SMO and supports several SVM methods (e.g. One-class SVM, nu-SVM, and R-SVM). Weka LibSVM (WLSVM) combines the merits of the two tools. WLSVM can be viewed as an implementation of the LibSVM running under Weka environment.<br>

- [GSON](http://mvnrepository.com/artifact/com.google.code.gson/gson/2.3.1) is a Java API, developed by Google, used to convert between Java objects and JSON objects.

- [ITEXT](https://github.com/itext/itextpdf) is an open source library that allows you to create and manipulate PDF documents. With this library is enabled users to see report of the sentiment analysis of movie review in PDF format by clicking on the printer icon.

- [Apache HTTPComponents](https://hc.apache.org/) it's used for API calls to Web Service IDOLOnDemand.The Apache HttpComponents™ project is responsible for creating and maintaining a toolset of low level Java components focused on HTTP and associated protocols. In this project we used httpclient-4.5. jar. httpcore-4.4.1.jar, appache-httpcomponents-httpclient.jar, also [commons-logging-1.2.jar](https://commons.apache.org/proper/commons-logging/download_logging.cgi).

# 5. Analysis

![Alt text](/images/table1.jpg?raw=true "Classification results") <br>

<<<<<<< HEAD
This section describes results of 5 different classification algorithms which are performed on the dataset ["data/movieReviewsTestDataset.arff"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/movieReviewsTestDataset.arff). 
=======
This section describes results of 5 different classification algorithms which are performed on the dataset ["data/movieReviews.arff"](https://github.com/DivnaP/MovieReviewsClassifier/blob/master/data/movieReviews.arff). 
>>>>>>> origin/master

The column *Precision* represent proportion of instances that are truly of a class divided by the total instances classified as that class. Column *Recall* represent proportion of instances classified as a given class divided by the actual total in that class. And, there is *F-Measure* which is a combined measure for precision and recall calculated as 

```
F-measure = 2 * Precision * Recall / (Precision + Recall)
```

<<<<<<< HEAD
The last column represents the number of correctly classified instances from the total of 1400.

As can be observed from the table, **Naive Bayes Multinomial** algorithm has the best precision rate, which also has the best recall. After that, the best result provides the Support vector machine algorithm. It has pretty good results, but in some cases while testing, application was unable to correctly classify obvious sentiment of a movie review. When another methods for analyzing a sentiment of movie review are used, like SentiWordNet and IDOLONDemand service, different results are achieved.
=======
The last column represents the number of correctly classified instances from the total of 2158.

As can be observed from the table, **Sequential minimal optimization** algorithm has the best precision rate, which also has the best recall. After that, the best result provides the Naive Bayes Multinomial algorithm. It has pretty good results, but in some cases while testing, application was unable to correctly classify obvious sentiment of a movie review. When another methods for analyzing a sentiment of movie review are used, like SentiWordNet and IDOLONDemand service, different results are achieved.
>>>>>>> origin/master

![Alt text](/images/ClassificationResult1.jpg?raw=true "Classification result when using classification based on training with SMO ")
![Alt text](/images/ClassificationResult2.jpg?raw=true "Classification result when using SentiWordNet")
![Alt text](/images/ClassificationResult3.jpg?raw=true "Classification result when using HPIDOLOnDemand")


# 6. References

[1] Wikipedia, "Logistic_regression", link:https://en.wikipedia.org/wiki/Logistic_regression, last access: 08.10.2015. <br>
[2] UniversityOfMinnesotaDuluth, "Chapter 5", link:http://www.d.umn.edu/~padhy005/Chapter5.html,last access: 08.10.2015. <br>
