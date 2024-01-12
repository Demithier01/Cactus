import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cactus.databinding.TypeItemBinding
import com.example.cactus.model.Cactus

class CactusAdapter(private var items: List<Cactus>) :
    RecyclerView.Adapter<CactusAdapter.CactusViewHolder>() {

    var onItemClick: ((Cactus) -> Unit)? = null


    class CactusViewHolder(private val binding: TypeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var textView: TextView = binding.textView
        var imageView: ImageView = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CactusViewHolder {
        val binding = TypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CactusViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CactusViewHolder, position: Int) {
        val cactus = items[position]

        // ใช้ Glide เพื่อโหลดรูปภาพและแสดงบน ImageView
        Glide.with(holder.itemView)
            .load(cactus.image)
            .into(holder.imageView)

        // กำหนดชื่อ Cactus ใน TextView
        holder.textView.text = cactus.name

        // กำหนดการทำงานเมื่อคลิกที่รายการ
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(cactus)
        }
    }

    // อัปเดตรายการเมื่อกรองตรง search
    fun setFilteredList(cactusList: List<Cactus>) {
        this.items = cactusList
        notifyDataSetChanged()
    }
}

