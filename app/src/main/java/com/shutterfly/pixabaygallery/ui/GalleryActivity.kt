package com.shutterfly.pixabaygallery.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.lifecycle.asFlow
import androidx.paging.compose.collectAsLazyPagingItems
import com.shutterfly.pixabaygallery.adapters.GalleryAdapter
import com.shutterfly.pixabaygallery.databinding.ActivityGalleryBinding
import com.shutterfly.pixabaygallery.repositories.GalleryRepository
import com.shutterfly.pixabaygallery.viewmodels.GalleryViewModel
import com.shutterfly.pixabaygallery.viewmodels.GalleryViewModelFactory

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    private val viewModel by viewModels<GalleryViewModel> {
        GalleryViewModelFactory(GalleryRepository())
    }

    private val galleryAdapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupUI()
//        setupClickListeners()
//        setupObservers()



        setContent {

            setComposeUI()
        }


    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    private fun setComposeUI() {
        viewModel.imageListObservable.asFlow().collectAsLazyPagingItems()
        val constraintsSet = ConstraintSet {
            val toolbar = createRefFor("toolbar")
            val editText = createRefFor("edittext")
            val recyclerView = createRefFor("recyclerView")
            constrain(toolbar) {   // arranging "composable 1" bounds
                top.linkTo(parent.top)    // linking "composable1" top to "parent" top
                start.linkTo(parent.start)  // linking "composable1" start to "parent" start
                end.linkTo(parent.end)   // linking "composable1" end to "parent" end
            }

            constrain(editText) {    // arranging "composable 2" bounds
                top.linkTo(toolbar.bottom)    // linking "composable2" top to "composable1" bottom
                start.linkTo(toolbar.start)  // linking "composable2" start to "composable1" start
            }

            constrain(recyclerView) {   // arranging "composable 3" bounds
                top.linkTo(editText.bottom)    // linking "composable3" top to "composable1" bottom
                end.linkTo(editText.start)  // linking "composable3" end to "composable1" end
            }

        }

        ConstraintLayout(
            constraintSet = constraintsSet,  // Don't forget to add "constraintsSet" variable
            modifier = Modifier  // The modifier assignment is upon you
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Blue)
                    .layoutId("toolbar")   // reference 'id'
            ){
                Text(
                    text = "Pixabay Gallery",  style = TextStyle(
                        color = Color.White,

                        // font size to change the
                        // size of our text view.
                        fontSize = 18.sp,

                        // font weight to bold such as light bold,
                        // extra bold to our text view.
                        fontWeight = FontWeight.Bold,

                        // font style is i=use to change style
                        // of our text view to italic and normal.
                        fontStyle = FontStyle.Normal,

                        // font family is use to change
                        // our font family to cursive.
                        fontFamily = FontFamily.Default,

                        // letter spacing is use to
                        // provide between letters.
                        letterSpacing = 2.sp,

                        // background is use to specify background
                        // color to our text view.

                        // shadow to make our
                        // text view elevated.
                        shadow = Shadow(color = Color.Gray),

                        // textALign to align our text view
                        // to center position.
                        textAlign = TextAlign.Center,
                    ),modifier = Modifier.padding(all = 5.dp)
                )
            }
            Box( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Blue)
                .layoutId("edittext")){
                val textState = remember { mutableStateOf(TextFieldValue("Search")) }
                TextField(
                    value = textState.value,
                    onValueChange = { value ->
                        textState.value = value
                        viewModel.onSearchButtonClicked(textState.value.toString())
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    },
                    trailingIcon = {
                        if (textState.value != TextFieldValue("")) {
                            IconButton(
                                onClick = {
                                    textState.value =
                                        TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                                }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .size(24.dp)
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            Box( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Blue)
                .layoutId("recyclerView")){
                LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    )){

                    items(viewModel.imageListObservable.) {
                        Card(
                            elevation = 8.dp,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.flower),
                                contentDescription = null
                            )
                        }
                    }

                }
            }
            }

        }


    private fun setupUI() {
        // disable search button since we start with an empty search text
        binding.searchButton.isEnabled = false
        // setup toolbar
        setSupportActionBar(binding.toolbar)
        // setup recycler view related stuff
        with(binding.recycler) {
            this.adapter = galleryAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun setupClickListeners() {
        binding.searchButton.setOnClickListener {
            val searchTerm = binding.searchText.text.toString()
            viewModel.onSearchButtonClicked(searchTerm)
        }
    }

    private fun setupObservers() {
        // observe for search input changes so we can enable/disable search button when it's empty
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.searchButton.isEnabled = s.isNotBlank()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // observe for new images data
        viewModel.imageListObservable.observe(this) { data ->
//            lifecycleScope.launch {
//                galleryAdapter.submitData(data)
//            }
        }
    }
}