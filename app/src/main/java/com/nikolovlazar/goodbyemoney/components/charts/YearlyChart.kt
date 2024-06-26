package com.nikolovlazar.goodbyemoney.components.charts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.nikolovlazar.goodbyemoney.models.Expense
import com.nikolovlazar.goodbyemoney.models.Recurrence
import com.nikolovlazar.goodbyemoney.models.groupedByDayOfWeek
import com.nikolovlazar.goodbyemoney.models.groupedByMonth
import com.nikolovlazar.goodbyemoney.ui.theme.LabelSecondary
import com.nikolovlazar.goodbyemoney.utils.simplifyNumber
import java.time.DayOfWeek
import java.time.Month

@Composable
fun YearlyChart(expenses: List<Expense>) {
  val groupedExpenses = expenses.groupedByMonth()

  BarChart(
    barChartData = BarChartData(
      bars = listOf(
        BarChartData.Bar(
          label = "Я",
          value = groupedExpenses[Month.JANUARY.name]?.total?.toFloat()
            ?: 0f,
          color = Color.White,
        ),
        BarChartData.Bar(
          label = "Ф",
          value = groupedExpenses[Month.FEBRUARY.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "М",
          value = groupedExpenses[Month.MARCH.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "А",

          value = groupedExpenses[Month.APRIL.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "М",
          value = groupedExpenses[Month.MAY.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "Ин",
          value = groupedExpenses[Month.JUNE.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "Ил",
          value = groupedExpenses[Month.JULY.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "А",
          value = groupedExpenses[Month.AUGUST.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "С",
          value = groupedExpenses[Month.SEPTEMBER.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "О",
          value = groupedExpenses[Month.OCTOBER.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "Н",
          value = groupedExpenses[Month.NOVEMBER.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
        BarChartData.Bar(
          label = "Д",
          value = groupedExpenses[Month.DECEMBER.name]?.total?.toFloat() ?: 0f,
          color = Color.White
        ),
      )
    ),
    labelDrawer = LabelDrawer(recurrence = Recurrence.Yearly),
    yAxisDrawer = SimpleYAxisDrawer(
      labelTextColor = LabelSecondary,
      labelValueFormatter = ::simplifyNumber,
      labelRatio = 7,
      labelTextSize = 14.sp
    ),
    barDrawer = BarDrawer(recurrence = Recurrence.Yearly),
    modifier = Modifier
      .padding(bottom = 20.dp)
      .fillMaxSize()
  )
}