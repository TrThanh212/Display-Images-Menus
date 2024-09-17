# **ImageView**
# Display Images from Local File

## Step 1: XML in `activity_main.xml`

Convert from `ConstraintLayout` to `LinearLayout`.

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Display images from local file"
        android:textSize="18sp"
        android:textStyle="bold"
        android:textColor="#000"
        android:layout_marginBottom="8dp"/>

    <!-- Local ImageView -->
    <ImageView
        android:id="@+id/myImageView"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:src="@drawable/lamnen"
        android:contentDescription="Display images from local file"
        android:scaleType="centerInside"
        android:layout_marginBottom="16dp"
        android:background="@android:color/darker_gray"
        android:padding="4dp"/>

    <!-- Change Image Button -->
    <Button
        android:id="@+id/changeImageButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Change Image"
        android:layout_marginBottom="24dp"
        android:padding="8dp"
        android:textColor="#FFF"
        android:backgroundTint="#FF6200EE"/>

</LinearLayout>
```

## Step 2: Java Code for Changing the Image

Get the `ImageView` using `findViewById`.

```java
private ImageView myImageView;
private Button changeImageButton;
private boolean isImage1 = true;

// In your onCreate method
myImageView = findViewById(R.id.myImageView);
changeImageButton = findViewById(R.id.changeImageButton);

changeImageButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (isImage1) {
            myImageView.setImageResource(R.drawable.hinh1);
            changeImageButton.setText("Change Image 1");
        } else {
            myImageView.setImageResource(R.drawable.lamnen);
            changeImageButton.setText("Change Image 2");
        }
        isImage1 = !isImage1;
    }
});
```

# Display Images from URL using Picasso

## Step 1: Add Internet Permission in `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Step 2: Add Picasso Library to `build.gradle`
[Link Picasso Library ](https://square.github.io/picasso/)
```gradle
dependencies {
    implementation 'com.squareup.picasso:picasso:2.8'
}
```

Sync your project after adding this.

## Step 3: XML in `activity_main.xml`

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!-- Picasso Section Title -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Display images from URL - Picasso"
        android:textSize="18sp"
        android:textStyle="bold"
        android:textColor="#000"
        android:layout_marginBottom="8dp"/>

    <!-- URL ImageView -->
    <ImageView
        android:id="@+id/UrlImageView"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:contentDescription="Display images from URL - Picasso"
        android:background="@android:color/darker_gray"
        android:padding="4dp"/>

</LinearLayout>
```

## Step 4: Load Image from URL Using Picasso

```java
private ImageView UrlImageView;

// Display images from URL - Picasso
String url = "https://cdn.pixabay.com/photo/2015/10/09/00/55/lotus-978659_640.jpg";

UrlImageView = findViewById(R.id.UrlImageView);

if (url == null || url.isEmpty()) {
    // If URL is null or empty, load placeholder/error image
    Picasso.get()
            .load(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(UrlImageView);
} else {
    // Load image from URL
    Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(UrlImageView);
}
```

# Display Menus

## Options Menu

### Step 1: Create Menu Resource

1. Create a folder named "menu" in `res/`.
2. Right-click on `res` -> New -> Directory.
3. Create a Menu Resource File named `menu_main.xml`.

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/action_settings"
        android:title="Settings"
        android:icon="@drawable/ic_action_setting"
        android:orderInCategory="1"
        app:showAsAction="ifRoom"/>

    <item
        android:id="@+id/action_favorites"
        android:title="Favorites"
        android:icon="@drawable/ic_favorite"
        android:orderInCategory="2"
        app:showAsAction="ifRoom"/>

</menu>
```

### Step 2: Java Code in `MainActivity`

- Create two new activities: `FavoriteActivity`, `SettingActivity`.
- Add the following XML to these activities:

```java
Button backToMainButton = findViewById(R.id.backToMainButton);

backToMainButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
```

### - Override `onCreateOptionsMenu()` in Activity

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
}
```

### - Override `onOptionsItemSelected()`

```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_settings) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    } else if (item.getItemId() == R.id.action_favorites) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }
    return super.onOptionsItemSelected(item);
}
```

# Contextual Menus

## Step 1: Create XML Menu Resource (`menu_context.xml`)

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/context_edit"
        android:title="Edit"
        android:orderInCategory="10"/>
    <item
        android:id="@+id/context_share"
        android:title="Share"
        android:orderInCategory="20"/>
</menu>
```

## Step 2: Create a TextView in `activity_main.xml` for Long Press

```xml
<TextView
    android:id="@+id/article"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="18sp"
    android:textStyle="bold"
    android:textColor="@color/design_default_color_error"
    android:text="Long press me for context menu" />
```

## Step 3: Register the View for a Context Menu in `onCreate`

```java
TextView articleText = findViewById(R.id.article);
registerForContextMenu(articleText);
```

## Step 4: 
## - Implement `onCreateContextMenu()`

```java
@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_context, menu);
}
```

## - Override `onContextItemSelected()`

```java
@Override
public boolean onContextItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.context_edit) {
        Toast.makeText(this, "Held down edit", Toast.LENGTH_SHORT).show();
        return true;
    } else if (item.getItemId() == R.id.context_share) {
        Toast.makeText(this, "Held down Share", Toast.LENGTH_SHORT).show();
        return true;
    } else {
        return super.onContextItemSelected(item);
    }
}
```
