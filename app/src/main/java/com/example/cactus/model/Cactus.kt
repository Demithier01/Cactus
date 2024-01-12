package com.example.cactus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Cactus(
    val name: String,
    val description: String,
    val image: String,
    val link : String
): Parcelable

object CactusGenerator {
    fun getItems(): List<Cactus> {
        return listOf(
            Cactus(
                name = "Astrophytum Asterias",
                description = "  Astrophytum asterias มีกำเนิดมาจากภูเขาและที่ดินทรายในภูมิภาคตะวันตกของอาร์เจนตินา มีลำต้นทรงกลมหรือทรงกระบอก และมีลักษณะรอยแห้งเป็นลายดาวหรือลายสามเหลี่ยมบนผิวลำต้น ทำให้ดูเป็นพิเศษและสวยงาม ดอกขนาดเล็กและสวยงาม สีขาวหรือเหลืองอ่อนและบานตอนกลางคืน\n" +
                        "   Astrophytum asterias เติบโตได้ดีในที่ที่มีแสงแดดเพียงพอ แต่ควรป้องกันจากรังแสงแดดร้อนที่มีช่วงเวลานานเกินไป เหมาะสำหรับการปลูกในสวนกลางแจ้งหรือในที่ที่มีแสงแดดอย่างเต็มที่\n",
                "https://www.picturethisai.com/wiki-image/1080/154221301999861783.jpeg",
                link = "https://data.addrun.org/plant/archives/576-astrophytum-asterias-zucc-lem"
            ),
            Cactus(
                name = "Echinopsis Calochlora",
                description = "  Echinopsis calochlora เป็นไม้ลูกเต่าที่มีกำเนิดมาจากภูเขาและที่ดินที่ร่วนปนทรายในภูมิภาคตะวันตกของอาร์เจนตินา มีลำต้นทรงกระบอกและทรงกระบอกแบบกระจก สูงได้ถึง 6-12 นิ้ว (15-30 ซม.) และมีเส้นผ่านศูนย์กลางที่ชัดเจน มีดอกสวยงามที่มีขนาดใหญ่สีขาวหรือสีเหลืองอ่อน ดอกจะบานตอนกลางคืน เป็นที่นิยมในการปลูกเพื่อการจัดแสดงและการตกแต่ง\n" +
                        "  Echinopsis calochlora เติบโตได้ดีในที่ที่มีแสงแดดเพียงพอ แต่สามารถป้องกันจากรังแสงแดดร้อนที่มีช่วงเวลานานเกินไป ทนทานต่ออากาศร้อนและแห้ง\n",
                "https://gabbarfarms.com/cdn/shop/products/echinopsis_calochlora_798x.jpg?v=1641134701",
                link = "https://indyptk.wixsite.com/cactusworld/post/%E0%B8%94%E0%B8%B2%E0%B8%A7%E0%B8%A5-%E0%B8%AD%E0%B8%A1%E0%B9%80%E0%B8%94-%E0%B8%AD%E0%B8%99-echinopsis-calochlora-k-schum"
            ),
            Cactus(
                name = "Euphorbia Decaryi",
                description = "  Euphorbia decaryi เป็นพืชที่มีรากที่เข้มและมีกำเนิดมาจากมาดากัสการ์ และทางตะวันตกเฉียงใต้ของมาดากัสการ์ มีลำต้นทรงกระบอก หรือทรงกระบอกที่มีชีวิต ลำต้นมีลักษณะรอยดำหรือดำเข้มที่ด้านบน มักออกดอกเป็นสีขาวหรือเหลือง\n" +
                        "  Euphorbia decaryi เติบโตได้ดีในที่ที่มีแสงแดดเพียงพอ แต่ควรป้องกันจากรังแสงแดดร้อนที่มีช่วงเวลานานเกินไป เหมาะสำหรับการปลูกในสวนกลางแจ้งหรือในที่ที่มีแสงแดดอย่างเต็มที่\n",
                "https://upload.wikimedia.org/wikipedia/commons/a/a0/Euphorbia_decaryi_var._spirosticha.jpg",
                link = "https://www.livingpop.com/euphorbiaceae-dino/"
            ),
            Cactus(
                name = "Gymnocalycium Baldianum",
                description = "  Gymnocalycium baldianum เป็นที่มาจากภูเขาและที่ดินทรายในภูมิภาคตะวันตกของอาร์เจนตินา มีลำต้นทรงกระบอก มักมีลายหรือลักษณะเด่นบนผิวลำต้น สีของลำต้นทั่วไปเป็นสีเขียว มีดอกขนาดเล็กที่บานตอนกลางคืน สีขาวหรือสีชมพูอ่อน\n" +
                        "  Gymnocalycium baldianum ชอบแสงแดดเต็มที่ ควรปลูกในที่ที่ได้รับแสงแดดมาก ทนทานต่ออากาศร้อนและแห้ง เป็นพืชที่ทนทานและเหมาะสำหรับการเลี้ยงในสวนสาธารณะหรือสวนบ้าน\n",
                "https://img.freepik.com/premium-photo/gymnocalycium-baldianum-cactus-with-beautiful-flowers-garden_484521-440.jpg",
                link = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwje_dDV0YmDAxX31zgGHQpXDOMQFnoECBMQAQ&url=https%3A%2F%2Fwww.1000maidee.com%2Fgymnocalycium-baldianum-%25E0%25B8%25AB%25E0%25B8%25A3%25E0%25B8%25B7%25E0%25B8%25AD-%25E0%25B8%259A%25E0%25B8%25B2%25E0%25B9%2580%25E0%25B8%2599%25E0%25B8%25B5%25E0%25B8%25A2%25E0%25B8%2599%25E0%25B8%25B1%25E0%25B8%25A1%2F&usg=AOvVaw0SRUKncSsAnlPmS9ZoNb7C&opi=89978449"
            ),
            Cactus(
                name = "Gymnocalycium Sepia",
                description = "  Gymnocalycium sepia เป็นไม้ลูกเต่าที่เจริญเติบโตในภูมิภาคที่แห้งและร้อนของอาร์เจนตินา มีลำต้นกลมหรือทรงกระบอก มีสีน้ำตาลหรือสีเข้มของลำต้นและขอบกลีบดอกทำให้ Gymnocalycium sepia มีลักษณะเด่นที่น่าสนใจ\n" +
                        "  Gymnocalycium sepia ต้องการแสงแดดเต็มที่ แต่อาจต้องป้องกันจากรังแสงที่มีความร้อนเกินไปในช่วงเวลาที่ร้อน มักจะเติบโตได้ดีในดินทรายหรือผสมดินที่มีการระบายน้ำดี ในสภาพอากาศที่มีอากาศแห้ง ต้องรักษาระดับความชื้นในดินให้เหมาะสม\n",
                "https://media.istockphoto.com/id/1319909408/th/%E0%B8%A3%E0%B8%B9%E0%B8%9B%E0%B8%96%E0%B9%88%E0%B8%B2%E0%B8%A2/gymnocalycium-mihanovichii-%E0%B9%80%E0%B8%9B%E0%B9%87%E0%B8%99%E0%B9%81%E0%B8%84%E0%B8%84%E0%B8%95%E0%B8%B1%E0%B8%AA%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B9%80%E0%B8%A0%E0%B8%97%E0%B8%AB%E0%B8%99%E0%B8%B6%E0%B9%88%E0%B8%87%E0%B8%97%E0%B8%B5%E0%B9%88%E0%B9%84%E0%B8%94%E0%B9%89%E0%B8%A3%E0%B8%B1%E0%B8%9A%E0%B8%81%E0%B8%B2%E0%B8%A3%E0%B8%AD%E0%B8%9A%E0%B8%A3%E0%B8%A1%E0%B8%88%E0%B8%B2%E0%B8%81%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B9%80%E0%B8%97%E0%B8%A8%E0%B9%84%E0%B8%97%E0%B8%A2.jpg?s=612x612&w=0&k=20&c=AN7FB8V8pNCpfIg9kdY4Sh2U_I61QewgytvpCIWq1js=",
                link = "https://www.livingpop.com/gymnocalycium-cactus/"
            ),
            Cactus(
                name = "Mammillaria Elongata",
                description = "  Gymnocalycium sepia เป็นไม้ลูกเต่าที่เจริญเติบโตในภูมิภาคที่แห้งและร้อนของอาร์เจนตินา มีลำต้นกลมหรือทรงกระบอก มีสีน้ำตาลหรือสีเข้มของลำต้นและขอบกลีบดอกทำให้ Gymnocalycium sepia มีลักษณะเด่นที่น่าสนใจ\n" +
                        "  Gymnocalycium sepia ต้องการแสงแดดเต็มที่ แต่อาจต้องป้องกันจากรังแสงที่มีความร้อนเกินไปในช่วงเวลาที่ร้อน มักจะเติบโตได้ดีในดินทรายหรือผสมดินที่มีการระบายน้ำดี ในสภาพอากาศที่มีอากาศแห้ง ต้องรักษาระดับความชื้นในดินให้เหมาะสม\n",
                "https://plantsam.com/wp-content/uploads/2022/01/Mammillaria-elongata.webp",
                link = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiMyaeB0omDAxVk8zgGHcnlB8YQFnoECAgQAw&url=https%3A%2F%2Fwww.picturethisai.com%2Fth%2Fcare%2FMammillaria_elongata.html&usg=AOvVaw0WH_RPlO7PwN9cMiLweO_m&opi=89978449"
            ),
            Cactus(
                name = "Mammillaria Gracilis",
                description = "  Gymnocalycium sepia เป็นไม้ลูกเต่าที่เจริญเติบโตในภูมิภาคที่แห้งและร้อนของอาร์เจนตินา มีลำต้นกลมหรือทรงกระบอก มีสีน้ำตาลหรือสีเข้มของลำต้นและขอบกลีบดอกทำให้ Gymnocalycium sepia มีลักษณะเด่นที่น่าสนใจ\n" +
                        "  Gymnocalycium sepia ต้องการแสงแดดเต็มที่ แต่อาจต้องป้องกันจากรังแสงที่มีความร้อนเกินไปในช่วงเวลาที่ร้อน มักจะเติบโตได้ดีในดินทรายหรือผสมดินที่มีการระบายน้ำดี ในสภาพอากาศที่มีอากาศแห้ง ต้องรักษาระดับความชื้นในดินให้เหมาะสม\n",
                "https://worldofsucculents.com/wp-content/uploads/2014/07/Mammillaria-vetula-subsp.-gracilis1.jpg",
                link = "https://data.addrun.org/plant/archives/958-mammillaria-gracilis-pfeiff"
            ),
            Cactus(
                name = "Mammillaria Prolifera",
                description = "  Gymnocalycium sepia เป็นไม้ลูกเต่าที่เจริญเติบโตในภูมิภาคที่แห้งและร้อนของอาร์เจนตินา มีลำต้นกลมหรือทรงกระบอก มีสีน้ำตาลหรือสีเข้มของลำต้นและขอบกลีบดอกทำให้ Gymnocalycium sepia มีลักษณะเด่นที่น่าสนใจ\n" +
                        "  Gymnocalycium sepia ต้องการแสงแดดเต็มที่ แต่อาจต้องป้องกันจากรังแสงที่มีความร้อนเกินไปในช่วงเวลาที่ร้อน มักจะเติบโตได้ดีในดินทรายหรือผสมดินที่มีการระบายน้ำดี ในสภาพอากาศที่มีอากาศแห้ง ต้องรักษาระดับความชื้นในดินให้เหมาะสม\n",
                "https://d2seqvvyy3b8p2.cloudfront.net/43d5dd15e37af2fdd39d0407d2a31e5c.jpg",
                link = "https://www.picturethisai.com/th/care/Mammillaria_prolifera.html"
            ),
            Cactus(
                name = "Opuntia Microdasys",
                description = "  Gymnocalycium sepia เป็นไม้ลูกเต่าที่เจริญเติบโตในภูมิภาคที่แห้งและร้อนของอาร์เจนตินา มีลำต้นกลมหรือทรงกระบอก มีสีน้ำตาลหรือสีเข้มของลำต้นและขอบกลีบดอกทำให้ Gymnocalycium sepia มีลักษณะเด่นที่น่าสนใจ\n" +
                        "  Gymnocalycium sepia ต้องการแสงแดดเต็มที่ แต่อาจต้องป้องกันจากรังแสงที่มีความร้อนเกินไปในช่วงเวลาที่ร้อน มักจะเติบโตได้ดีในดินทรายหรือผสมดินที่มีการระบายน้ำดี ในสภาพอากาศที่มีอากาศแห้ง ต้องรักษาระดับความชื้นในดินให้เหมาะสม\n",
                "https://www.primrose.co.uk/media/catalog/product/cache/acf06ef1ca6b6cd54f153540f7686931/H/P/HP30001021_1678.jpg",
                link = "https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwiok4300omDAxU_8TgGHRdyCvIQFnoECAgQAw&url=https%3A%2F%2Fwww.picturethisai.com%2Fth%2Fwiki%2FOpuntia_microdasys.html&usg=AOvVaw3aY03Led848mWTFPn9wraR&opi=89978449"
            ),
        )
    }
}
