package com.example.noteapp
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adapter(private  var listData: List<Data>,private  var context: Context):RecyclerView.Adapter<Adapter.ViewHolder>() {
   private val db:DBHelper = DBHelper(context)
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var txt1 : TextView = view.findViewById(R.id.title_txt)
        var txt2 : TextView = view.findViewById(R.id.description_txt)
        var editBtn: ImageView = view.findViewById(R.id.edit)
        var deleteBtn: ImageView = view.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt1.text = listData[position].title
        holder.txt2.text = listData[position].content
        holder.editBtn.setOnClickListener {
            var intent = Intent(holder.itemView.context,EditActivity::class.java).apply {
                putExtra("noteId",listData[position].id)
            }
            holder.itemView.context.startActivity(intent)

        }
        holder.deleteBtn.setOnClickListener {
            /*val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Confirm")
            builder.setMessage("Are you sure you want to proceed?")
            builder.setPositiveButton("Yes") { dialog, which ->*/
                db.delete(listData[position].id)
                refreshData(db.getData())
                db.close()
             Toast.makeText(holder.itemView.context,"Note Deleted",Toast.LENGTH_SHORT).show()
           /* }
            builder.setNegativeButton("No") { dialog, which ->

                dialog.dismiss()
            }*/

        }

    }
    fun refreshData(dataList:List<Data>){
        listData = dataList
        notifyDataSetChanged()
    }

}