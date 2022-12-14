package com.bemo.graduationproject.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContract
import com.bemo.graduationproject.Classes.User
import com.bemo.graduationproject.FirebaseStorageManager
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
/*
https://www.youtube.com/watch?v=ATj6tq5HQZU
    private val cropActivityResultContent = object:ActivityResultContract<Any?,Uri?>(){
        override fun createIntent(context: Context, input: Any?): Intent {
            return Crop
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            TODO("Not yet implemented")
        }

    }
  */
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var grade: String

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userImageUri: Uri
    private lateinit var imageView: ImageView
companion object{
    val IMAGE_REQUEST_CODE =100
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
grade=""
        auth = Firebase.auth

        database = Firebase.database.reference

        imageView = binding.signUserImage

        val gradeList = resources.getStringArray(R.array.grades)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,R.array.grades,R.layout.spinner_item)
        val autoCom = findViewById<Spinner>(R.id.grade_spinner)
        autoCom.setAdapter(adapter)

        autoCom.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0 : AdapterView<*>?, p1: View?, p2:Int, p3: Long) {
                grade =gradeList[p2]
                binding.t.text=grade}
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        binding.imageClick.setOnClickListener {
            pickImageFromGallery()
        }
binding.signUpBt.setOnClickListener {

    val email=binding.signEmailAddress.text.toString()
    val password = binding.signPassword.text.toString()
    val code = binding.signCode.text.toString()
    val fullName = binding.signName.text.toString()
    if (userImageUri == null){
        if (email.isNotEmpty()&&password.isNotEmpty() &&code.isNotEmpty()&&fullName.isNotEmpty()&&grade.isNotEmpty()){
            if (password.length == 14 || true ){  // just for testing after tht remove true
              auth.createUserWithEmailAndPassword(email, password)
                  .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Successful operation",Toast.LENGTH_SHORT).show()
                    val userId = auth.currentUser?.uid

                    if (userId !=null){
                        FirebaseStorageManager().uploadImage(this,userImageUri, userId)
                        addUserData(userId,fullName,code.toInt(),password.toInt(),grade)
                        startActivity(Intent(this,HomeScreen::class.java))

                    }

                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
}else{
    Toast.makeText(this,"make sure to write the 14 number of the national id",Toast.LENGTH_SHORT).show()

}
    }else{
        Toast.makeText(this,"all data required",Toast.LENGTH_SHORT).show()

    }
    }else{
        Toast.makeText(this,"make sure to choose pic",Toast.LENGTH_SHORT).show()

    }
}

    }
private fun pickImageFromGallery(){
    val intent = Intent (Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(intent, IMAGE_REQUEST_CODE)

}
    private fun pickImageFromGalleryAndCrop(){
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK)
        {
            userImageUri = data?.data!!
            imageView.setImageURI(userImageUri)
        }
    }


    private fun addUserData( userId:String,userName:String,code:Int,nationalId:Int,grade:String){
        database.child("Users").child(userId).setValue(User(userId,userName,code,nationalId,grade))
            .addOnSuccessListener {
                Toast.makeText(this,"data pushed", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }
    }
}