package com.primo.feature.presentation.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.android.material.R
import com.primo.common_ui.component.ChipCategoryItem
import com.primo.common_ui.component.FlexibleTopBar
import com.primo.common_ui.component.HtmlText
import com.primo.common_ui.component.LoadingIndicator
import com.primo.common_ui.component.TryAgainButton
import com.primo.common_ui.theme.AppTypography
import com.primo.domain.entity.FeedDetail
import com.primo.domain.entity.FeedDetailUIModel
import com.primo.domain.entity.FeedProfile
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDetailScreen(
    feedId: Int?,
    feedDetailViewModel: FeedDetailViewModel = koinViewModel(),
    onClickBack: () -> Unit
) {

    LaunchedEffect(feedDetailViewModel) {
        feedDetailViewModel.getFeedDetail(feedId)
    }

    val uiState: FeedDetailUIState by feedDetailViewModel.uiState.collectAsState()

    when (uiState) {
        FeedDetailUIState.Error -> TryAgainButton {
            feedDetailViewModel.getFeedDetail(feedId)
        }

        FeedDetailUIState.Idle -> Box {

        }

        FeedDetailUIState.Loading -> LoadingIndicator()
        is FeedDetailUIState.Success -> {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

            val scrollState = rememberScrollState()
            val feedDetail = (uiState as FeedDetailUIState.Success).feedDetail

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    FlexibleTopBar(scrollBehavior = scrollBehavior) {
                        TopAppBar(
                            navigationIcon = {
                                IconButton(onClick = {
                                    onClickBack.invoke()
                                }) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_arrow_back_black_24),
                                        contentDescription = "ic_arrow_back_black_24"
                                    )
                                }
                            },
                            title = {
                            },
                            actions = {
                                Icon(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    painter = painterResource(R.drawable.abc_ic_menu_share_mtrl_alpha),
                                    contentDescription = "ic_arrow_back_black_24"
                                )
                            })
                    }
                }

            ) {
                FeedDetailContent(
                    modifier = Modifier.padding(it),
                    uiModel = feedDetail,
                    scrollState = scrollState
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeedDetailContent(modifier: Modifier, uiModel: FeedDetailUIModel, scrollState: ScrollState) {
    val feedDetail = uiModel.feedDetail
    val profile = uiModel.profile
    Column(modifier = modifier.verticalScroll(scrollState)) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = feedDetail.title,
            style = AppTypography.displayLarge
        )

        Row(
            modifier = Modifier.padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = profile.logo,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentDescription = profile.logo,
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = profile.title, style = AppTypography.headlineLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "1 min read : ${feedDetail.pubDate}", style = AppTypography.titleMedium,
                )
            }
        }

        AsyncImage(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            model = feedDetail.image,
            contentDescription = feedDetail.image,
            contentScale = ContentScale.FillWidth
        )
        HtmlText(
            modifier = Modifier.padding(horizontal = 16.dp), text = feedDetail.content
        )
        FlowRow(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)) {
            feedDetail.categories.forEach {
                ChipCategoryItem(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    text = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedDetailPreview() {
    FeedDetailContent(
        modifier = Modifier,
        scrollState = rememberScrollState(),
        uiModel = FeedDetailUIModel(
            profile = FeedProfile(
                "Vee PRIMO",
                "https://cdn-images-1.medium.com/max/723/1*KwapjAVN6IDBbLyoBgbCsw.png"
            ),
            feedDetail = FeedDetail(
                id = 0,
                link = "https://medium.com/@primoapp/%E0%B8%97%E0%B8%B3%E0%B8%AD%E0%B8%A2%E0%B9%88%E0%B8%B2%E0%B8%87%E0%B9%84%E0%B8%A3%E0%B9%83%E0%B8%AB%E0%B9%89%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%AA%E0%B8%9A%E0%B8%81%E0%B8%B2%E0%B8%A3%E0%B8%93%E0%B9%8C%E0%B8%82%E0%B8%AD%E0%B8%87%E0%B8%9C%E0%B8%B9%E0%B9%89%E0%B8%9A%E0%B8%A3%E0%B8%B4%E0%B9%82%E0%B8%A0%E0%B8%84-personalize-%E0%B8%88%E0%B8%B2%E0%B8%81%E0%B8%AD%E0%B8%AD%E0%B8%99%E0%B9%84%E0%B8%A5%E0%B8%99%E0%B9%8C%E0%B9%84%E0%B8%9B%E0%B8%AA%E0%B8%B9%E0%B9%88%E0%B8%AD%E0%B8%AD%E0%B8%9F%E0%B9%84%E0%B8%A5%E0%B8%99%E0%B9%8C%E0%B9%84%E0%B8%94%E0%B9%89-fbb480b720b4?source=rss-80d3fbeb257------2",
                content = "ทุกวันนี้ผู้บริโภคคาดหวังกับประสบการณ์ช้อปปิ้งแบบ Personalize ทั้งบนออนไลน์และออฟไลน์ ในความจริงที่ว่า 73% ของผู้บริโภคเลือกที่จะทำธุรกิจกับแบรนด์ที่ใช้ข้อมูลในการสร้างประสบการณ์ช้อปปิ้งแบบ Personalize</p><p><strong>Harvard Business พบว่าการตลาดแบบ Personalization สามารถนำไปสู่</strong></p><p>การลดค่าใช้จ่ายในการหาลูกค้าใหม่ได้ถึง 50%</p><p>การเพิ่มขึ้นของรายได้ 5–15%</p><p>การเพิ่มประสิทธิภาพของค่าใช้จ่ายทางการตลาดได้ 10–30%</p><p>เนื่องจาก Personalization เป็นวิธีที่ได้ผลลัพธ์ดี เพราะทำให้แบรนด์สามารถตัดสิ่งที่ไม่จำเป็นและพูดคุยกับลูกค้าเจาะจงแต่ละคนได้โดยตรง แบรนด์ของคุณจะสามารถอยู่ในสายตาของลูกค้ามากขึ้น และลูกค้าเองก็ได้รับประสบการณ์การช้อปปิ้งที่ถูกปรับให้เหมาะสมกับตัวเอง แม้ว่าจะผ่านช่องทางอื่น สิ่งนี้เป็นประโยชน์ร่วมกันเพื่อให้เข้าสู่ความสัมพันธ์ระหว่างแบรนด์กับลูกค้า กระตุ้นยอดขายเพิ่มขึ้นและให้ลูกค้าเดิมกลับมาซื้อซ้ำ จึงเป็นสิ่งสำคัญสำหรับนักการตลาดเพื่อให้เข้าใจการเปลี่ยนแปลงจากออนไลน์สู่ออฟไลน์ และทำอย่างไรให้สื่อสารได้ครอบคลุมทุก Touch Point</p><p><strong>ทำให้ Mobile Shopper เปลี่ยนไปที่หน้าร้านค้าและบนมือถือ</strong></p><p>หนึ่งในช่องทางที่เปลี่ยนแปลงได้ง่ายที่สุดคือโทรศัพท์มือถือ ในบางครั้งนักช้อปเหล่านี้จะเปลี่ยนไปช้อปทางออนไลน์ แต่ยังคงมียอดซื้อสูงทั้งที่ร้านและบนมือถือ</p><p>ตามที่กล่าวถึงบน Google มากกว่า 57% ของ Mobile Shopper มีแนวโน้มว่าจะไปที่หน้าร้าน มากกว่า 39% มีแนวโน้มที่จะติดต่อไปยังธุรกิจ และ มากกว่า 51% มีแนวโน้มที่จะทำการสั่งซื้อ</p><p>ผลการศึกษายังแสดงให้เห็นว่า 78% ของ Mobile Shopper ทำการสั่งซื้อภายในวันเดียวกับที่เสิร์ชทางมือถือ และ 50% จะไปที่หน้าร้าน ในปี 2019 การเสิร์ชทางมือถือจึงถูกคาดการณ์ว่าจะเป็นแรงผลักดันผู้คนมากกว่า 60 ล้านคนให้ติดต่อไปยังธุรกิจ</p><p><strong>Personalize ประสบการณ์ในร้านค้าปลีก</strong></p><p>คุณสามารถใช้ลูกค้าที่มาที่หน้าร้านเพื่อสร้างความสัมพันธ์และความน่าดึงดูดของแบรนด์ การรู้ว่าทำไมลูกค้าถึงมาที่ร้านค้าจะเป็นก้าวแรกของการไขปัญหาในการสร้าง personalize สาเหตุที่ลูกค้ามาบ่อยๆเพราะในร้านค้ามีบรรยากาศที่ในออนไลน์ไม่มี หน้าร้านค้าสามารถพบเจอผู้คนได้อย่างเป็นมิตร สามารถสัมผัสและมีความรู้สึกของการเห็นสินค้าก่อนการซื้อ และมีหลายวิธีเพื่อให้ลูกค้ามั่นใจว่าการได้รับประสบการณ์ในร้านค้าจะทำให้พวกเขารู้สึกเหมือนว่าเป็นส่วนหนึ่งของแบรนด์</p><p><strong>ลงทุนกับเทคโนโลยีในร้านค้า</strong></p><p>แบรนด์สามารถทำความเข้าใจได้มากขึ้นว่าทำอย่างไรผู้ซื้อถึงจะมี interact กับหน้าร้านค้า โดยที่ผ่านมาก็มีความพยายามที่จะใช้เทคโนโลยี Beacon ในการ Engage ผ่านสัญญาณ Bluetooth แต่อย่างไรก็ตาม Beacon อาจใช้งานได้ไม่เต็มประสิทธิภาพเพราะพฤติกรรมผู้ซื้อในปัจจุบันไม่นิยมเปิด Bluetooth ทิ้งไว้ จึงมีเทคโนโลยีทางเลือกอีกแบบที่ไม่ต้องอาศัยสัญญาณ Bluetooth นั่นคือ “Real-time mobile phone sensing” ซึ่งทำงานด้วยการปล่อยสัญญาณ WIFI ตามกลุ่มสถานที่ และตรวจจับสัญญาณจากมือถือ และเมื่อลูกค้าเชื่อมต่อสัญญาณ WIFI ที่กำหนดก็จะผูกข้อมูลลูกค้าเข้ากับสัญญาณที่ตรวจจับได้ โดยที่ลูกค้าไม่จำเป็นต้องเชื่อมต่อ Bluetooth และเมื่อลูกค้ากลับมาสถานที่นั้นอีกครั้ง ก็จะสามารถส่งโฆษณาแบบ personalize เพื่อกระตุ้นให้ลูกค้าเกิดการซื้อที่หน้าร้านได้ทันที และยังสามารถเก็บข้อมูลเพื่อทำ Data analytics ต่อไปได้อีกด้วย</p><p>การช่วยเหลือและให้ข้อมูลได้อย่างถูกต้องจากพนักงานก็เป็นอีกสิ่งสำคัญของการสร้างประสบการณ์ช้อปปิ้งที่หน้าร้านค้า มากกว่า 50% ของผู้ซื้อบอกว่าการได้เห็นร้านค้าใช้มือถือร่วมกับการทำงานของพนักงาน ทำให้เขามั่นใจขึ้นว่าพนักงานเหล่านี้จะสามารถให้ข้อมูลที่ถูกต้องและบริการที่รวดเร็ว ดังนั้นคุณควรจัดหาอุปกรณ์เพื่อให้พนักงานหาข้อมูลได้อย่างรวดเร็วและต้องเข้าถึงง่าย</p><p>Self-service kiosk เป็นอีกตัวช่วยหนึ่งที่ทำให้การช้อปปิ้งง่ายดายขึ้น Kiosk ทำให้ลูกค้ารู้ได้เลยว่ามีสต๊อกสินค้าเหลืออยู่เท่าไหร่ ทำการซื้อขายและเช็คสถานะได้ หรือแม้กระทั่งช้อปออนไลน์ในขณะที่ยังอยู่ในร้านค้า</p><p><strong>สร้างประสบการณ์การช้อปปิ้ง</strong></p><p>การสร้างประสบการณ์ด้วยการใช้ประสาทสัมผัสทั้ง 5 ในร้านค้าปลีกเป็นรูปแบบการตลาดที่ผู้คนให้การยอมรับ โดยการจัดงานอีเว้นท์ที่สร้างประสบการณ์ที่ดื่มด่ำ มีความหมาย และสามารถสื่อสารสิ่งที่แบรนด์ต้องการให้แก่ลูกค้า ทำให้แบรนด์และนักการตลาดสร้างสัมพันธ์กับลูกค้าได้มากขึ้น</p><p>การจัดอีเว้นท์ในร้านค้าที่เหมาะกับความสนใจของลูกค้า เชิญลูกค้า VIP มาเพื่อรับส่วนลดพิเศษ และจัดอีเว้นท์ที่สะท้อนภาพลักษณ์ของแบรนด์เพื่อเสนอสิ่งใหม่ๆให้ลูกค้าที่มาหน้าร้าน เช่น IKEA จัดงานนอนข้ามคืนที่คลังสินค้าให้แก่ผู้ชนะกิจกรรมใน Facebook หรือ Apple ที่สร้างโปรแกรม “Today at Apple” ให้ลูกค้าที่เข้ามาใช้บริการที่ร้านค้าเข้าร่วมได้ฟรี โดยจะมีกิจกรรมหลากหลายครอบคลุมความสนใจของคนหลายกลุ่ม</p><p>ทุกวันนี้ลูกค้าจะเห็นแบรนด์เป็นหนึ่งเดียวกัน ไม่ได้แยกเป็นหลายช่องทาง ด้วยเหตุผลนี้ลูกค้าจึงคาดหวังประสบการณ์ช้อปปิ้งที่มีคุณภาพและเปลี่ยนจากออนไลน์มาสู่ออฟไลน์ได้อย่างแนบเนียน นักการตลาดจึงต้องมีความรับผิดชอบในการสร้างประสบการณ์ช้อปปิ้งให้ลูกค้าได้รับอย่างครบถ้วน",
                title = "ทำอย่างไรให้ประสบการณ์ของผู้บริโภค Personalize จากออนไลน์ไปสู่ออฟไลน์ได้",
                categories = listOf(
                    "personalization",
                    "Retail",
                    "Shopping",
                    "Online",
                ),
                author = "Vee PRIMO",
                pubDate = "1 min read : Jun 12, 2019",
                image = "https://cdn-images-1.medium.com/max/723/1*KwapjAVN6IDBbLyoBgbCsw.png",
            )
        )
    )
}


