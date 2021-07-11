<html>
<style>

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  user-select: none;
}
.bg-img{
  background: url('image3.jpg');
  height: 100vh;
  background-size: cover;
  background-position: center;
}
.bg-img:after{
  position: absolute;
  content: '';
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0,0,0,0.7);
}
.content{
  position: absolute;
  top: 50%;
  left: 50%;
  z-index: 999;
  text-align: center;
  padding: 60px 32px;
  width: 370px;
  transform: translate(-50%,-50%);
  background: rgba(255,255,255,0.04);
  box-shadow: -1px 4px 28px 0px rgba(0,0,0,0.75);
}
.content header{
  color: white;
  font-size: 33px;
  font-weight: 600;
  margin: 0 0 35px 0;
  font-family: 'Montserrat',sans-serif;
}
.field{
  position: relative;
  height: 45px;
  width: 100%;
  display: flex;
  background: rgba(255,255,255,0.94);
}
.field span{
  color: #222;
  width: 40px;
  line-height: 45px;
}
.field input{
  height: 100%;
  width: 100%;
  background: transparent;
  border: none;
  outline: none;
  color: #222;
  font-size: 16px;
  font-family: 'Poppins',sans-serif;
}
.space{
  margin-top: 16px;
}
.show{
  position: absolute;
  right: 13px;
  font-size: 13px;
  font-weight: 700;
  color: #222;
  display: none;
  cursor: pointer;
  font-family: 'Montserrat',sans-serif;
}
.pass-key:valid ~ .show{
  display: block;
}
.pass{
  text-align: left;
  margin: 10px 0;
}
.pass a{
  color: white;
  text-decoration: none;
  font-family: 'Poppins',sans-serif;
}
.pass:hover a{
  text-decoration: underline;
}
.field input[type="submit"]{
  background: #3498db;
  border: 1px solid #2691d9;
  color: white;
  font-size: 18px;
  letter-spacing: 1px;
  font-weight: 600;
  cursor: pointer;
  font-family: 'Montserrat',sans-serif;
}
.field input[type="submit"]:hover{

  background: #2691d9;
}


</style>

<script>
function validate(){
let psw=document.getElementById("password").value;
let rpsw=document.getElementById("repeat-password").value;
let minNumberofChars = 6;
let maxNumberofChars = 16;
let passwordExpression  = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;

		
        if(!passwordExpression.test(psw)){
        	alert("password should contain atleast one number and one special character");
        	return (false)
        }
        if(psw!=rpsw){
			alert("Password And Repeat Password are not same");
			return (false)
		}
        return (true)
}
</script>

<body>
<form action="/aa/update" onsubmit="return validate()" method="post" >
      <div class="bg-img">
         <div class="content">
            <header>Update Password</header>
               <div class>
                  <span class="fa fa-user"></span>
                  <input type="hidden"  value=${email} name="email" id="email">
               </div>
               <div class="field space">
                  <span class="fa fa-lock"></span>
                  <input type="password" class="pass-key" required placeholder="Enter OTP" name="otp" id="otp">
                  <span class="show">SHOW</span>
               </div>
              
               <div class="field space">
                  <span class="fa fa-lock"></span>
                  <input type="password" class="pass-key" required placeholder="Enter New Password" name="password" id="password">
                  <span class="show">SHOW</span>
               </div>
               <div class="field space">
                  <span class="fa fa-lock"></span>
                  <input type="password" class="pass-key" required placeholder="Repeat Password" name="repeat-password" id="repeat-password">
                  <span class="show">SHOW</span>
               </div>
               <div class="field space">
                  <input type="submit" value="Submit">
               </div>
            
            
         </div>
      </div>
   </form>   
   </body>
</html>


</body>




</html>