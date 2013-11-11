Movie_recommender_Java
======================

This is a movie recommender system using item based collaborative filtering algorithm. The language is Java. 

In this project, I worked on a movie recommender system. 
The raw dataset is the 100k movielens dataset 100000 ratings by 943 users on 1682 items. Each user has rated at least 20 movies. 
I applied item based collaborative filtering algorithm on the ratings, find the similarity between the movies using Pearson-correlated similarity, 
and predict the ratings a user will give to a movie. The data structure used in the project includes Hashmap, Arraylist. 
For instance, when I need to find a certain user's ratings, I put the user ID as key, and the rated movies as the values. 
The algorithms include item based collaborative filtering, insertion sort, etc.

Implementation Part I, Preprocessing
The movielens 100k dataset contains the following data: 
The full user data set, 100000 ratings by 943 users on 1682 items.
Each user has rated at least 20 movies.  Users and items are numbered consecutively from 1.  The data is randomly ordered. This is a tab separated list of :
user id | item id | rating | timestamp. 

Firstly, I randomly choose 70% of the ratings dataset as training data, and 30% as test data. This part of codes is in training_test.java. 
I processed the ratings dataset so that I can get the movies that has been rated by a user, the codes are in user_rated_movie.java; 
the ratings for each movie, in movie_user_ratings.java. From the movies dataset, I got the movies that are in the same genre, in movie_genra.java. 
HashMap has been used a lot in data preprocessing. 

Implementation Part II, Item based collaborative filtering
I applied the item based collaborative filtering algorithm to get the similarities among the movies. 
The basic idea of collaborative filtering algorithm is to provide item recommendations based on the similarity of the items. 
If a user likes A and A is similar to B, then most probably the user will like B. So we can recommend B to the user. 
I used Pearson correlation to compute the similarity.

ImplementationPart III, Prediction
Prediction is made based on the similarities between the movies as well as the user’s ratings on the certain movies. 
In order to find the prediction on movie a by a certain user u, I find the all the movies that had been rated by the user.
Then compute the similarities between each of these movies with the given movie. 
After these steps, compute the prediction.
Then I computed the root squared mean error(RSME).

Part IV, Recommendation
Based on the prediction, we can choose top n movies that have highest predicted ratings to the user.
As a result of this project, For user 186, the RMSE is 2.77
And the top 10 recommendations for this user would be: 
566, Clear and Present Danger  (1994)
568, Speed (1994)
934, Preacher's Wife, The (1996)
106, Diabolique (1996)
306, Mrs. Brown (Her Majesty, Mrs. Brown) (1997)
79, Fugitive, The (1993)
243, Jungle2Jungle (1997)
95, Aladdin (1992)
596, Hunchback of Notre Dame, The (1996)
226, Die Hard 2 (1990)

