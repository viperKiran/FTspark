package FTSparkDriver;

/**
 * Created by aparna on 28/01/15.
 */
public class rddData {
    private String name;
    private String line;
    private String operation;
    private int line_no;
    private int rdd_no;
    private double time_to_compute;
    private double time_to_check_point;
    private double time_to_restore;
    private double memory_occupied;
    processRegisteringRDD rddthread;

    public rddData(processRegisteringRDD rddthread)
    {
        this.rddthread=rddthread;
        time_to_check_point=0;
        time_to_compute=0;
        time_to_restore=0;
    }

    void processLine(String line)
    {
       /* this.line=line;
        String temp[] = line.split(" ");
        rdd_no=Integer.parseInt(temp[6]);
        operation=temp[7].substring(1);
        String t[]=temp[9].split(":");
        String t1=t[1].substring(0, t[1].length()-1);
        line_no=Integer.parseInt(t1);
        print();
        FindName();*/
try {
    this.line = line; // Now the line considered is the criticality line
    String t = line;
    t = t.replaceAll("( )+", " ");
    t = t.trim();
    //System.out.println(t);
    String temp[] = t.split(" ");

    String t1[] = temp[temp.length - 2].split(":");
    int l = Integer.parseInt(t1[1]);
    this.line_no = l;
    String r;
    this.operation = temp[temp.length - 4];
    if(temp[0].equals("|") && temp[1].equals("|"))
    {
        if(temp.length==8) {
            String rn[] = temp[2].split("\\[");
            r = rn[1].substring(0, rn[1].length() - 1);
        }
        else
        {
            String rn[] = temp[3].split("\\[");
            r = rn[1].substring(0, rn[1].length() - 1);
            //System.out.println(r);
        }
        rdd_no=Integer.parseInt(r);
    }
    else
    {
        if(temp.length==7) {
            String rn[] = temp[1].split("\\[");
            r = rn[1].substring(0, rn[1].length() - 1);
          //  System.out.println(r);
        }
        else
        {
            String rn[] = temp[2].split("\\[");
            r = rn[1].substring(0, rn[1].length() - 1);
           // System.out.println(r);
        }
        rdd_no=Integer.parseInt(r);
    }


    //print();
    FindName();

}
catch(Exception e)
{
    e.printStackTrace();
    System.out.println("Exception at line "+line);
}

    }
    void FindName()
    {

        String FileLine=rddthread.ftDriver.Filelines[line_no-1];
        //System.out.println("Number "+rdd_no+" FileLine "+FileLine);
        if(FileLine.matches(".*=.*"))
        {
        FileLine=FileLine.replaceAll(" ","");
        String temp[]=FileLine.split("=");
         name=temp[0];
        }
        else
        {
            FileLine=FileLine.replaceAll(" ","");
            String temp[]=FileLine.split("[.]");
            name=temp[0];
        }
    }



    int getLineNo()
    {
        return line_no;
    }
    String getName()
    {
        return name;
    }
    int getRdd_no() { return rdd_no; };

    String getOperation()
    {
        return operation;
    }

    double getTime_to_compute()
    {
        return time_to_compute;
    }

    double getTime_to_restore()
    {
        return time_to_restore;
    }
    double getTime_to_check_point()
    {
        return time_to_check_point;
    }
    double getMemory_occupied()
    {
        return memory_occupied;
    }
    void setTime_to_compute(double time)
    {
     time_to_compute=time;
    }
    void setTime_to_check_point(double time)
    {
        time_to_check_point=time;
    }
    void setTime_to_restore(double time)
    {
        time_to_restore=time;
    }
    void setMemory_occupied(double memory)
    {
        memory_occupied=memory;
    }
    void print()
    {

        System.out.println("line "+line);
        System.out.println("name "+name);
        System.out.println("operation "+operation);
        System.out.println("line no "+line_no);
        System.out.println("rdd no "+rdd_no);
        System.out.println("Memory Ocuupied "+memory_occupied);
        System.out.println("Time to compute "+time_to_compute);
        System.out.println();
    }
}
