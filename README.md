Movie_recommender_Java
======================

This is a movie recommender system using item based collaborative filtering algorithm. The language is Java. 

In this project, I worked on a movie recommender system. 
The raw dataset is the 1M movielens dataset comprising 6040 movies and 3952 users' ratings. 
I applied item based collaborative filtering algorithm on the ratings, find the similarity between the movies using Pearson-correlated similarity, 
and predict the ratings a user will give to a movie. The data structure used in the project includes Hashmap, Arraylist. 
For instance, when I need to find a certain user's ratings, I put the user ID as key, and the rated movies as the values. 
The algorithms include item based collaborative filtering, insertion sort, etc.

Implementation Part I, Preprocessing
The movielens 1M dataset contains the following data:
A ratings.dat file contains the ratings on the 6040 movies by 3952 users in the format: Movie ID :: User ID :: rating :: timestamp
A movies.dat file contains the movie information in the format:
Movie ID :: movie name :: movie genre
A users.dat file contains the users information in the format:
UserID :: Gender :: Age :: Occupation :: Zip-code

Firstly, I randomly choose of the ratings dataset as training data, and as test data. This part of codes is in training_test.java.
I processed the ratings dataset so that I can get the movies that has been rated by a user, the codes are in user_rated_movie.java; 
the ratings for each movie, in movie_user_ratings.java. 
From the movies dataset, I got the movies that are in the same genre, in movie_genra.java. 

Implementation Part II, Item based collaborative filtering
I applied the item based collaborative filtering algorithm to get the similarities among the movies. 
The basic idea of collaborative filtering algorithm is to provide item recommendations based on the similarity of the items. 
If a user likes A and A is similar to B, then most probably the user will like B. So we can recommend B to the user. 
I used Pearson correlation.Ì±Öùö•)

ImplementationPart III, Prediction
Prediction is made based on the similarities between the movies as well as the user‚Äôs ratings on the certain movies. 
In order to find the prediction on movie a by a certain user u, I first find the movies that are in the same genre with movie a, name the set S. 
And then find the movies in S that had been rated by the user. 
After these steps, compute the prediction.Ì±†, ùëé |
It is calculated by how similar the movies have been rated by the user, taking into account their similarity on the specific movie.
Then I computed the root squared mean error(RSME).

Part IV, Recommendation
Based on the prediction, we can choose top n movies that have highest predicted ratings to the user.
