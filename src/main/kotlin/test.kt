import java.sql.DriverManager
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException

object test {
    @JvmStatic
    fun connection() {


        try {

            val c = DriverManager.getConnection(
                "jdbc:mysql://localhost/students?serverTimezone=UTC",
                "root",
                ""
            )
            print("Done!")
            val s = c.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)
           val r= s.executeQuery("SELECT * FROM `users` WHERE `id` =1")
            val rm = r.metaData
          print("${ rm.columnCount}")
            print("${rm.getColumnType(90)}")
            print(rm.getColumnName(90))

            print("ok!!!")
            r.moveToInsertRow()
            r.updateInt("id",90)
            r.updateString("Name","Mostafa")
            r.updateString("city","cairo")
            r.updateString("city","cairo")

            var i=0
            while(r.next()){
                i=r.row
                print(r)
                if(r.row==3){
                    r.updateString("name","Mostafa")
                    r.updateString("city","cairo")
                    r.updateString("phone","01017242252")
                    r.updateRow()
                }
                if(r.row==4){
                    r.deleteRow()
                    // عشان مايطبعش الصف الذي تم حذفه
                    continue
                }

                println("name : ${r.getString("id")}")
                println("name : ${r.getString("name")}")
                println("name : ${r.getString("city")}")
                println("name : ${r.getString("phone")}")


            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }



    }

}