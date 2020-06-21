package com.example.firebasechatkotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference// ...
    private lateinit var postReference: DatabaseReference
    private lateinit var postKey: String
    private var postListener: ValueEventListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtMensaje=findViewById<TextView>(R.id.txtMensaje)
        val txtId=findViewById<TextView>(R.id.txtId)
        val txtUsuario=findViewById<TextView>(R.id.txtUsuario)
        val txtDestino=findViewById<TextView>(R.id.txtDestino)

        postReference = FirebaseDatabase.getInstance().reference
            .child("mensajes")

        val btnEnviar=findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            // [START initialize_database_ref]
            database = FirebaseDatabase.getInstance().reference
            // [END initialize_database_ref]
            val key = database.child("mensajes").push().key

            if (key == null) {
               // Log.w("error", "Couldn't get push key for posts")
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

            }

            val destino=this.txtDestino.getText().toString()
            val uid=this.txtId.getText().toString()
            val usuario=this.txtUsuario.getText().toString()
            val mensaje= this.txtMensaje.getText().toString()
            val post = Post(uid, usuario, destino, mensaje)
            val postValues = post.toMap()

            val childUpdates = HashMap<String, Any>()
            childUpdates["/mensajes/$destino/$key"] = postValues
            childUpdates["/mensajes_usuario/$uid/$destino/$key"] = postValues

            database.updateChildren(childUpdates)
        }


    }
    public override fun onStart() {
        super.onStart()

        // Add value event listener to the post
        // [START post_value_event_listener]
        val postListener = object : ValueEventListener {
            @SuppressLint("WrongConstant")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue(Post::class.java)
                // [START_EXCLUDE]
                post?.let {
                    Toast.makeText(this@MainActivity,"llego",1)

                }
                // [END_EXCLUDE]
            }

            @SuppressLint("WrongConstant")
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message

                Toast.makeText(this@MainActivity,"error",1)
                // [END_EXCLUDE]
            }
        }
        postReference.addValueEventListener(postListener)
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        this.postListener = postListener

        // Listen for comments

    }
}



// [START post_class]
@IgnoreExtraProperties
data class Post(
    var uid: String? = "",
    var emisor: String? = "",
    var destino: String? = "",
    var mensaje: String? = "",
    var starCount: Int = 0,
    var stars: MutableMap<String, Boolean> = HashMap()
) {

    // [START post_to_map]
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "emisor" to emisor,
            "destino" to destino,
            "mensaje" to mensaje

        )
    }
    // [END post_to_map]
}
// [END post_class]