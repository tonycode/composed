package dev.tonycode.composed.comida.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.components.ImageButton
import dev.tonycode.composed.comida.ui.preview.ScreenPreview
import dev.tonycode.composed.comida.ui.screenHorizontalPadding
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun CartScreen() {
    Column {
        // top bar
        ConstraintLayout(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start = screenHorizontalPadding,
                        top = 12.dp,
                        end = screenHorizontalPadding,
                        bottom = 0.dp,
                    ),
        ) {
            val (backButton, title) = createRefs()

            // back button
            ImageButton(
                onClick = { },
                modifier =
                    Modifier.constrainAs(backButton) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            ) {
                Image(
                    painterResource(id = R.drawable.comida_nav_back),
                    contentDescription = "Navigate back",
                )
            }

            // screen title
            Text(
                "Cart",
                style = MaterialTheme.typography.titleMedium,
                modifier =
                    Modifier.constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            )
        }

        Spacer(Modifier.height(24.dp))

        // cart items list

        BillBlock(
            modifier = Modifier.padding(horizontal = screenHorizontalPadding),
        )

        Spacer(Modifier.height(24.dp))

        BottomBlock()
    }
}

@Composable
private fun BillBlock(modifier: Modifier = Modifier) {
    Column(modifier) {
        BillRow("Subtotal", BigDecimal(12.20))
        Spacer(Modifier.height(4.dp))
        BillRow("Tax and Fees", BigDecimal(4.10))
        Spacer(Modifier.height(4.dp))
        BillRow("Delivery", BigDecimal(1))
    }
}

@Composable
private fun BillRow(
    title: String,
    value: BigDecimal,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )

        Text(
            "$${ value.setScale(2, RoundingMode.CEILING).toPlainString() }",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun BottomBlock() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = ComidaPalette.PrimaryText,
    ) {
        Column(
            modifier = Modifier.padding(start = 0.dp, top = 20.dp, end = 0.dp, bottom = 0.dp),
        ) {
            Text(
                "Delivery Address",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = screenHorizontalPadding),
            )

            Spacer(Modifier.height(4.dp))

            Text(
                "Avinguda Meridiana, 350, 358, 08027 Barcelona",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = screenHorizontalPadding),
            )

            Spacer(Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                color = ComidaPalette.Black,
            ) {
                Row(
                    modifier =
                        Modifier.padding(
                            start = screenHorizontalPadding,
                            top = 20.dp,
                            end = screenHorizontalPadding,
                            bottom = 24.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Total",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                    )

                    Text(
                        "$17.30",
                        style = MaterialTheme.typography.headlineLarge,
                    )

                    ElevatedButton(
                        onClick = { },
                        modifier = Modifier.height(48.dp),
                        border = null,
                        colors =
                            ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                            ),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    ) {
                        Text(
                            "Go to checkout",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CartScreenPreview() =
    ScreenPreview {
        CartScreen()
    }
