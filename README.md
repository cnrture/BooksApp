# BooksApp with MVVM & Retrofit & Navigation

### Project Features
 - MVVM (Model, View, ViewModel)
 - LiveData
 - DataBinding
 - Retrofit
 - BottomNavigation
 - ViewPager2 in TabLayout 
 - SearchView in Toolbar
 - Picasso for images

### Login Screen

- TabLayout & ViewPager2 used for Sign In and Sign Up screens.

<p align="left">
<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/sign_in_screen.png" width="250" height="530"/>
<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/sign_up_screen.png" width="250" height="530"/>
</p>

### Main Screen

- Books listed using RecyclerView + DataBinding.
- Book search feature works with author, book name and publisher name. Search can be made with the SearchView in the Toolbar or the ChipButtons at the top of the page.
- When the book image is pressed, the CustomAlertView opens and details are displayed. The book is sent to the basket with the Add to Cart button.
- The "+" button in the Toolbar is used to add a book. (Note: Images are added with url.)

<p align="left">
<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/books_screen.png" width="250" height="530"/>

<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/book_detail.png" width="250" height="530"/>

<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/search.png" width="250" height="530"/>
  
<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/add_book.png" width="250" height="530"/>
</p>

### Cart Screen

- Books in cart are pulled and listed with RecyclerView.
- Pressing the delete button deletes the book from the basket.
<p align="left">
<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/book_cart.png" width="250" height="530"/>
</p>

### Profile Screen

- User information is kept using SharedPreferences when a member is logged in. When this page is entered, information from the SharedPreferences structure is retrieved and displayed.

<p align="left">
<img src="https://github.com/cnrture/BooksApp/blob/main/Screenshots/profile_screen.png" width="250" height="530"/>
</p>
