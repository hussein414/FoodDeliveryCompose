package com.example.fooddeliverycompose

import android.os.Bundle
import android.preference.PreferenceActivity.Header
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliverycompose.data.CategoryModel
import com.example.fooddeliverycompose.data.PopularModel
import com.example.fooddeliverycompose.ui.theme.*
import com.example.fooddeliverycompose.utils.Destinations
import com.example.fooddeliverycompose.utils.Destinations.HOME
import com.example.fooddeliverycompose.utils.FakeData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryComposeTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = HOME, builder = {
                    composable(HOME) {
                        HomeScreen(navController = navController)
                    }
                    composable(Destinations.DETAIL) {
                        DetailScreen(navController = navController)
                    }
                })
            }
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp)
    ) {
        Column {
            HeaderHomeParent()

            Spacer(modifier = Modifier.size(40.dp))

            OrderBox()

            Spacer(modifier = Modifier.size(40.dp))

            Text(
                text = "Categories",
                style = Typography.body1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = BlackTextColor
            )

            Spacer(modifier = Modifier.size(40.dp))


            CategoryRecyclerView(category = FakeData.categoryFakeData)


            Spacer(modifier = Modifier.size(40.dp))


            Text(
                text = "Popular",
                style = Typography.body1,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = BlackTextColor
            )
            Spacer(modifier = Modifier.size(40.dp))

            PopularRecyclerView(
                popularModel = FakeData.popularFakeDara,
                navController = navController
            )

        }
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 10.dp, 10.dp)
            .background(White), contentAlignment = Alignment.Center
    ) {
        val data = navController.previousBackStackEntry?.arguments?.getParcelable<PopularModel>(
            Destinations.FoodData
        )
        if (data != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                DetailHeader(navController = navController)

                Image(
                    painter = painterResource(id = data.resId),
                    contentDescription = "",
                    modifier = Modifier.size(275.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                )
                {
                    Column(verticalArrangement = Arrangement.SpaceBetween) {

                        Text(
                            text = data.title, style = Typography.body1,
                            fontSize = 22.sp,
                            color = BlackTextColor
                        )


                        Box(
                            modifier = Modifier
                                .height(40.dp),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "$",
                                    style = Typography.body1,
                                    fontSize = 14.sp,
                                    color = Orange500
                                )

                                Text(
                                    text = "${data.price}",
                                    style = Typography.body1,
                                    fontSize = 20.sp,
                                    color = BlackTextColor
                                )
                            }
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        BoxWithRes(
                            resId = R.drawable.minus,
                            descriptor = "Minus",
                            iconSize = 16,
                            boxSize = 36,
                            iconColor = BlackTextColor
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = "01",
                            style = Typography.body2,
                            fontSize = 18.sp,
                            color = BlackTextColor
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        BoxWithRes(
                            resId = R.drawable.add,
                            descriptor = "Add",
                            iconSize = 16,
                            boxSize = 36,
                            iconColor = Color.White,
                            backgroundColor = Yellow500
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = data.description,
                    style = Typography.h5,
                    fontSize = 16.sp,
                    color = TextColor,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                DetailBox(data = data)

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Ingradients",
                    style = Typography.body1,
                    fontSize = 22.sp,
                    color = BlackTextColor,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    items(data.ingradients.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    CardItemBg
                                ), contentAlignment = Alignment.Center
                        )
                        {
                            Image(
                                painter = painterResource(id = data.ingradients[index]),
                                contentDescription = "",
                                modifier = Modifier.size(width = 30.dp, height = 24.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .size(width = 203.dp, height = 56.dp)
                        .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                        .background(
                            Yellow500
                        ), contentAlignment = Alignment.Center
                )
                {
                    Text(text = "Add to card", style = Typography.body1, color = Color.White)
                }


            }
        }
    }
}


@Composable
fun DetailBox(data: PopularModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(
                CardItemBg
            )
            .padding(15.dp)
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Row {
                Image(
                    painter = painterResource(id = R.drawable.calori),
                    contentDescription = "Calori",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "${data.calori} kcal",
                    style = Typography.body2,
                    color = BlackTextColor
                )
            }

            Divider(
                color = DividerColor, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )


            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Star",
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "${data.rate}",
                    style = Typography.body2,
                    color = BlackTextColor
                )
            }

            Divider(
                color = DividerColor, modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Row {

                Image(
                    painter = painterResource(id = R.drawable.schedule),
                    contentDescription = "Schedule",
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "${data.scheduleTime} Min",
                    style = Typography.body2,
                    color = BlackTextColor
                )
            }

        }
    }
}


@Composable
fun DetailHeader(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        BoxWithRes(
            resId = R.drawable.arrow_left, descriptor = "Left"
        )



        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    CardItemBg
                ), contentAlignment = Alignment.Center
        )
        {
            Box(
                modifier = Modifier
                    .size(24.dp)
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.bag),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = IconColor
                )

                Box(
                    modifier = Modifier
                        .padding(top = 2.dp, end = 2.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(
                                Yellow500
                            )
                    )
                }
            }
        }
    }
}


@Composable
fun OrderBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Yellow200)
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(color = BlackTextColor)) {
                        append("The Fastest In \n " + "Delivery")
                    }

                    withStyle(style = SpanStyle(color = Yellow500)) {
                        append(" Food")
                    }
                })
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .size(width = 125.dp, height = 40.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .background(Yellow500), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Order Now",
                        style = Typography.body1,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = White
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "Man",
                modifier = Modifier
                    .size(156.dp)
                    .padding(start = 20.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            )
        }
    }
}


@Composable
fun HeaderHomeParent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        BoxWithRes(
            resId = R.drawable.menu,
            descriptor = "menu",
            backgroundColor = Orange500,
            iconColor = White
        )

        Row {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "location",
                modifier = Modifier.size(16.dp),
                tint = Orange500
            )
            Spacer(modifier = Modifier.size(8.dp))

            Text(text = "BaBol,Mazandran")


            Spacer(modifier = Modifier.size(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "location",
                modifier = Modifier.size(16.dp),
                tint = Orange500
            )

        }

        BoxWithRes(
            resId = R.drawable.search,
            descriptor = "search",
            backgroundColor = Orange500,
            iconColor = White
        )
    }
}


@Composable
fun BoxWithRes(
    resId: Int,
    descriptor: String,
    backgroundColor: Color? = CardItemBg,
    iconColor: Color? = IconColor,
    boxSize: Int? = 40,
    iconSize: Int = 24
) {
    Box(
        modifier = Modifier
            .size(boxSize!!.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor!!), contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = descriptor,
            modifier = Modifier.size(iconSize.dp),
            tint = iconColor!!
        )
    }
}


@Composable
fun CategoryRecyclerView(category: List<CategoryModel>) {
    val selectedIndex = remember {
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        items(category.size) { index ->
            CategoryItem(category = category[index], selectedIndex = selectedIndex, index = index)
        }
    }
}

@Composable
fun PopularRecyclerView(popularModel: List<PopularModel>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(popularModel.size) { index ->
            PopularItem(popularModel = popularModel[index], navController)
        }
    }
}

@Composable
fun CategoryItem(category: CategoryModel, selectedIndex: MutableState<Int>, index: Int) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 140.dp)
            .padding(start = 5.dp, top = 0.dp, 0.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                selectedIndex.value = index
            }
            .background(
                if (selectedIndex.value == index) Yellow500 else CardItemBg
            ), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = category.restId),
                contentDescription = category.title,
                modifier = Modifier.size(48.dp),
                tint = if (selectedIndex.value == index) White else BlackTextColor
            )
            Spacer(modifier = Modifier.size(13.dp))
            Text(
                text = category.title,
                style = Typography.body2,
                fontSize = 14.sp,
                color = if (selectedIndex.value == index) White else BlackTextColor
            )
        }
    }
}


@Composable
fun PopularItem(popularModel: PopularModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.currentBackStackEntry?.arguments = Bundle().apply {
                    putParcelable(Destinations.FoodData, popularModel)
                }
                navController.navigate(Destinations.DETAIL)
            }
            .padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(end = 10.dp)
                .clip(
                    RoundedCornerShape(18.dp)
                )
                .background(CardItemBg)
        )

        Column(modifier = Modifier.padding(start = 20.dp)) {
            Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.crown),
                        contentDescription = "crown", modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "Best Selling",
                        style = Typography.h5,
                        fontSize = 14.sp,
                        color = TextColor
                    )
                }
            }
            Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = popularModel.title,
                        style = Typography.body1,
                        fontSize = 22.sp,
                        color = BlackTextColor
                    )
                }
            }
            Box(modifier = Modifier.height(40.dp), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$",
                        style = Typography.body1,
                        fontSize = 16.sp,
                        color = Orange500
                    )

                    Text(
                        text = "${popularModel.price}",
                        style = Typography.body1,
                        fontSize = 22.sp,
                        color = BlackTextColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 40.dp)
                        .clip(RoundedCornerShape(bottomStart = 18.dp, topEnd = 18.dp))
                        .background(Yellow500), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "add",
                        modifier = Modifier.size(20.dp), tint = White
                    )
                }

                Spacer(modifier = Modifier.width(48.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "star",
                        modifier = Modifier.size(16.dp), tint = BlackTextColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${popularModel.rate}",
                        style = Typography.body1,
                        color = BlackTextColor
                    )
                }
            }
        }


        Image(
            painter = painterResource(popularModel.resId),
            contentDescription = popularModel.title,
            Modifier
                .size(156.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodDeliveryComposeTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}