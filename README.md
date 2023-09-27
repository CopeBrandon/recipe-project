# Recipe-Refresh

### Description

This is a recipe portfolio website, allowing users to create and manipulate their recipes, save anyone's recipes in
favorites or add any recipe to your weekly menu. Once you have a recipe you want to try, you can even alter the serving
size of the meal and the ingredient quantities will scale with the desired serving size. If you wish, you can also
search the website with our regular or advance search options.

#### Tech Stack
* Language - Java, JavaScript
*  Framework - SpringBoot
*  Template engine - Thymeleaf
*  Database engine - SQL
*  Other libraries or components - BootStrap



>### Database Config and Installation
>1. Download MySQL Workbench if you haven't already. Download Link Here: https://dev.mysql.com/downloads/workbench/
>2. With that downloaded, set up a new Schema. Name your Schema recipe-project to match the info in the application.properties file.
>3. Next set up a user in the Administration tab, found in the bottom left quadrant of your screen, next to the Schema tab
>4. Then, go to "Users and Privileges" and create a new user. Using "recipe-project" as the name to
>5. Now change "Limit to hosts matching:" from its default of "%" to "localhost"
>6. Then you'll want the password to be set to "cuzwegottaeat", matching the password in the application.properties of this program.
>7. Select the "Schema Privileges" tab and then click the add-entry button. Select all available checkboxes and then click "apply".
>8. Now your database is set up and ready to receive data from the application\
    ></br>
>##### Database Credentials
> Schema name: recipe-project </br>
> Username: recipe-project </br>
> Password: cuzwegottaeat

### Running program
1. In order to run the project, Open up your IDE. </br>
   (We used IntelliJ to build and test our application. To download IntelliJ follow these instructions: https://www.jetbrains.com/help/idea/installation-guide.html#toolbox) </br>
   (If you wish to use Visual Studio Code, follow this tutorial: https://code.visualstudio.com/docs/java/java-spring-boot)
2. Now that you have your IDE open, you'll want to bootrun the program. Click on "Gradle" on the right, vertical task bar, just under the notifications tab.
3. With the Gradle window open, you'll want to select the current project's drop down. From there, click on the "Tasks" folder and go to application.
4. Once Inside the application drop down inside gradle, double click bootRun. This should begin running the program.
5. After the program has finished setting up, Go to the URL: "localhost:8080" in your web browser of choice. From there, you should be able to access any part of the website as you wish.

### Using the application
1. On initial set up, you won't have any recipes already populated in your database as this is not set up with a permanent persistent server database.
2. First thing you'll want to do is login/signup for an account. You can easily make an account for this program, or you can sign in with Google or Github. A link to the login page can be found in the top nav bar, or you will be redirected when trying to perform an action that requires you to be signed in.
3. Now that you're signed in, you'll be able to add recipes to the database. You can simply follow the link in the Nav bar to reach the new recipe form.
4. Once on the new recipe form, You'll be able to add more fields for ingredients and instructions, as well as add a picture to represent the dish. Using the link at the bottom of the page, user's can also add tags that can be used to help other users find your recipes, but the tags are not necessary for creating a recipe.
5. Feel free to make a few recipes to help populate the database on your personal machine. You should have access to all features of the application now that you have recipes to interact with.

### Features
>#### Sean Feuerhelm Features
>* CRUD operations: In order to interact with your data, the application allows users to perform CRUD operations in a user-friendly way. Simply Use the Create Recipe form to CREATE recipes. Once they are added to the database, user's have the option to EDIT and DELETE their recipes while viewing them. The DELETE feature is a soft-delete feature, meaning admins will be the only users who can fully delete data.
>* Serving Size Conversion: While viewing a recipe, it's original serving size and portions are initially displayed. However, while viewing the recipe, the user may choose to change the serving/portion size of the recipe. They simply have to change the portion number and hit "convert" and all the ingredients will scale along with the serving size.
   > </br> Github: https://github.com/ParmaSeanCheese

>#### Paul Jackson Features
>* Spring Security with OAuth2: Implemented OAuth2 for user creation and log in with tokens from Google and Github.
>* User Login and Registration: Users can create an account and log in to save data to their account.
>* Multipart File Uploads (Images): Users can upload images with their recipes. Images are saved dynamically and loaded in real-time.
>* User-validated Likes and Ratings: Allows a signed-in user to like and rate recipes.
>* Controller Services: A service to return one of three user types. Another service to return recipe information.
   > </br> Github: https://github.com/paul-jackson-dev

>#### Kelsey Jungermann Features
> *
> Github: https://github.com/kdj275

>#### Max Wingate Features
> Github: https://github.com/Noobwastaken99

>#### Jennifer Kelly Features
> Github:https://github.com/jenbilyeu80


### Screenshots
#### Homepage
![Homepage](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/homepage.png)
#### Login
![Login](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/login.gif)
#### Create, Edit, Delete
![Create-Edit-Delete](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/create.gif)
#### View and Convert
![View-Convert-Recipe](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/convert.gif)
#### Like and Rate
![Like-Rate](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/like-rate.gif)
#### Search
![Search](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/search.png)
#### Advanced Search
![Advanced-Search](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/advanced-search.gif)
#### Comment
![Comment](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/comments.png)
#### Favorites
![Favorites](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/favorites.png)
#### My Recipies
![My-Recipies](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/my-recipies.gif)
#### Menu
![Menu](https://raw.githubusercontent.com/CopeBrandon/recipe-project/p-readme/src/main/resources/static/images/screenshots/menu.gif)


