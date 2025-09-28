# 1. Introduction
## Recipe Sharing & Meal Planning Platform
We decided on a Recipe and Meal Sharing "Platform" which would allow users to create and share recipes for meals much like a social media platform where recipes can be posted, tagged, and reviewed by other users. We're looking to put in a bit of work into the UI to make it look a bit like X/Twitter, where one can scroll down a list of recipes from a user.
### Core Features:
- User authentication and profiles - we'll be looking to include these in a technical sense, but to allow for time on the rest of the project, the profiles and authentication steps will likely be the bare minimum.
- Recipe creation, editing, and sharing - The main feature of the app will focus on the recipes, listing ingredients, and posting the meal.
- Shopping list generation - As this is ingredient focused, there'll be a feature to generate a shopping list based off of the recipes you view.
- Recipe rating and reviews - Acting like our version of liking/retweeting posts, meals will be able to be rated by other users from 1-5 Stars
- Meal planning calendar - If time permits, we plan to include a calendar that one can add meals to, whether by links to a post or copying a meal to the day.


### Technical Implementation:
- UI Layer: Templates with Bootstrap for recipe browsing, meal planning calendar

- Business Logic: Recipe recommendation engine (by ratings), meal plan algorithms, shopping list optimization

- Persistence: JPA/Hibernate for recipes, users, meal plans, ratings

- External Integration: Storage for recipe images

- JSON APIs: RESTful endpoints for mobile app potential


# 2. Storyboard - ?
### A user writing a recipe and posting it
### A user reviewing a recipe and rating it

# 3. Functional Requirements - Jaecar
### FR-1: User Registration

The system shall have users and validate them.

### FR-2: User Profiles

The system shall allow users to view and edit their posts.
The system shall display user statistics.

### FR-3: User Authorization

The system shall ensure users can only edit/delete their own recipes.
The system shall allow authenticated users to view and rate recipes.

### FR-4: Profile Management

The system shall display a user's created recipes on their profile page.
The system shall show user's recipe statistics and ratings received.

### FR-5: Recipe Creation

The system shall allow authenticated users to create new recipes.
The system shall require recipe name, ingredients list, and cooking instructions.
The system shall allow users to add optional fields: cooking time, servings, difficulty level.
The system shall auto-assign unique recipe IDs.

### FR-6: Recipe Editing

The system shall allow recipe authors to edit their existing recipes.
The system shall validate all required fields during updates.

### FR-7: Recipe Deletion

The system shall allow recipe authors to delete their own recipes.
The system shall confirm deletion actions with user prompts.

### FR-8: Recipe Viewing

The system shall display recipes in a social media feed format.
The system shall show recipe details including ingredients, instructions, and ratings.
The system shall provide individual recipe detail pages.

### FR-9: Recipe Search

The system shall allow users to search recipes by name.
The system shall support filtering.

### FR-10: Recipe Categories/Tags

The system shall allow users to categorize recipes (breakfast, lunch, dinner, dessert, etc.).
The system shall support multiple tags per recipe.
The system shall provide filtering by categories and tags.

### FR-11: Recipe Images

The system shall allow users to upload images for their recipes.
The system shall support common image formats.
The system shall resize and optimize images for display.
The system shall provide default placeholder images for recipes without photos.

### FR-12: Recipe Sharing

The system shall provide shareable links for individual recipes.
The system shall generate social media sharing options.
The system shall allow recipe printing functionality.

### FR-13: Recipe Recommendations

The system shall recommend recipes based on user ratings.
The system shall display highly-rated recipes prominently.
The system shall suggest similar recipes based on ingredients.

### FR-14: Recipe Rating

The system shall allow authenticated users to rate recipes on a 1-5 star scale
The system shall prevent users from rating their own recipes
The system shall allow users to update their existing ratings

### FR-15: Rating Display

The system shall display average ratings for each recipe.
The system shall show the total number of ratings received.
The system shall display ratings in a visually clear star format.

### FR-16: Review Comments (Bonus)

The system may allow users to leave text reviews with ratings.
The system may provide comment moderation functionality.
The system may display recent reviews on recipe pages.

### FR-17: Rating Validation

The system shall validate rating values are within 1-5 range.
The system shall prevent duplicate ratings from the same user for the same recipe.
The system shall handle rating updates and recalculate averages.

### FR-18: Response Time

The system shall load recipe feeds quickly.
The system shall provide search results quickly.
The system shall handle concurrent user sessions efficiently.

### FR-19: Data Persistence

The system shall store all data persistently.
The system shall maintain data integrity and consistency.

### FR-20: API Functionality

The system shall provide RESTful JSON APIs for all major functions.
The system may support mobile application integration.
The system shall implement proper HTTP status codes and error handling.

### FR-21: Responsive Design

The system may provide mobile-friendly responsive design.
The system shall work consistently across modern web browsers.
The system may support touch interfaces for mobile devices.

### FR-22: User Experience

The system shall provide intuitive navigation and user flows.
The system shall display clear error messages and validation feedback.

### FR-23: Visual Design

The system shall implement a consistent visual design system.
The system shall provide clear visual hierarchy and typography.




## Time Permitting
### FR-24: Shopping List Generation (Bonus)

The system shall generate shopping lists from selected recipes
The system shall combine duplicate ingredients automatically
The system shall allow users to add custom items to shopping lists

### FR-25: Shopping List Management (Bonus)

The system shall allow users to create multiple named shopping lists.
The system shall provide functionality to edit shopping list items.
The system shall support checking off completed items.

### FR-26: Shopping List Export (Bonus)

The system shall allow users to export shopping lists as text.
The system shall provide email functionality for sharing lists.
The system shall support printing shopping lists.

### FR-27: Shopping List Persistence (Bonus)

The system shall save shopping lists for registered users
The system shall maintain shopping list history
The system shall allow deletion of completed shopping lists

### FR-28: Calendar Integration

The system may provide a calendar view for meal planning.
The system may allow users to assign recipes to specific dates.
The system may support weekly and monthly calendar views.

### FR-29: Meal Plan Creation

The system may allow users to create structured meal plans.
The system may support meal plans for multiple days/weeks.
The system may provide meal plan templates.

### FR-30: Meal Plan Management

The system may allow editing and rearranging of planned meals
The system may provide meal plan sharing functionality
The system may support copying meal plans

### FR-31: Calendar Export

The system may export meal plans to external calendar applications.
The system may provide meal plan reminders.
The system may integrate with third-party calendar services.

# 4. Class Diagram - Eric
![Class Diagram](ClassDiagramImage/Class_Diagram_v1.png)


# 5. JSON Scheme - Eric

Schema for Recipe
> {
>  "type": "object",
>  "properties": {
>    "recipeId": { "type": "integer" },
>    "recipeName": { "type": "string" },
>    "recipeIngredients": {
>      "type": "array",
>      "items": { "type": "string" }
>    },
>    "recipeRating": { "type": "integer" }
>  }
> }

Schema for User
> {
>  "type": "object",
>  "properties": {
>    "userId": { "type": "integer" },
>    "userName": { "type": "string" },
>    "userEmail": { "type": "string", "format": "email" },
>    "userPassword": { "type": "string" },
>    "numberOfRecipes": { "type": "integer" },
>    "profileDescription": { "type": "string" }
>  }
> }



# 6. Scrum Roles
Eric Coomer - (Temporary) GitHub Admin/Product Owner/Scrum Master, (Likely) Business Logic and Persistence

Alicia Davoyan - 

Jaecar Ly - (Possible) Github Admin/Product Owner/Scrum Master

Dan Rice - No Preference

Hari Sathyanarayanan - 

# 7. [Github.com Link](https://github.com/Eric-C2/Enterprise-Application-Development-Group-8)

# 8. A Scrum or Kanban board - LucidSpark? Jaecar

# 9. [Link to Teams](https://teams.microsoft.com/l/chat/19:a7b58f8bc1514dba87d412eaeeb0b98c@thread.v2/conversations?context=%7B%22contextType%22%3A%22chat%22%7D)
