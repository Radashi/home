package com.example.loginprueba

class singUP {
    private lateint var binding: ActivitySingUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater.from(this))
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvGoToSingIn.setOnClickListener {
            val intent = Intent(this, singIN::class.java)
            startActivity(intent)
        }
        binding.btnSingUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            val confirmPass = binding.etConfirmPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, singIN::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "No coincide password..!!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Datos incorrectos...!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}