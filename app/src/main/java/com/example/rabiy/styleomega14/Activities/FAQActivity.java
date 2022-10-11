package com.example.rabiy.styleomega14.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.rabiy.styleomega14.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FAQActivity extends AppCompatActivity {

    private ListView lvFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.itemtoolbar);
        toolbar.setTitle("FAQ");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        lvFaq=findViewById(R.id.list_view_faq);

        HashMap<String,String> faquestions=new HashMap<>();
        faquestions.put("Q : Do you restock items that are sold out?","Unfortunately we do not restock most of our items. Please visit our Back in Stock section to see what popular items we brought back to our online store. You may also contact your local store to check availability. Both our stores and Customer Service Team will need an item number/product code to confirm availability.");
        faquestions.put("Q : Can I modify or cancel my order?","Due to our efforts to ensure that you receive your order as quickly and accurately as possible, we are unable to cancel or make changes to your order after you have placed it with us");
        faquestions.put("Q : How do I know what size to choose?","View our complete size guide for men, women, and kids.");
        faquestions.put("Q : I Think My Package Was Lost Or Stolen, What Do I Do Now?","Style Omega is not responsible for lost or stolen packages. The carrier will generally determine if your order can be left in a safe secure place at your delivery address. If you believe your package may have been lost or stolen, please contact our customer service team so we can assist you");
        faquestions.put("Q : Why was my order cancelled?","StyleOmega was unable to verify the billing information that was entered on the order. If the billing address does not match with what the credit card issuer or bank has on file, the order will be automatically canceled.");
        faquestions.put("Q : What is your return policy?","Online purchases made through the StyleOmega are valid for exchange, credit, or refund within 30 days from the ship date.");
        faquestions.put("Q : How soon will I receive a refund for my return?","Please allow 2-3 weeks from the return shipped date for your account to be credited, and 1-2 billing cycles for the credit to appear on your statement.");

        List<HashMap<String,String>> faqs=new ArrayList<>();
        SimpleAdapter adapter= new SimpleAdapter(this,faqs,R.layout.inquiries_list,
                new String[]{"First Line","Second Line"},
                new int[]{R.id.tv_inquiry,R.id.tv_inquiry_reply});

        Iterator iterator=faquestions.entrySet().iterator();
        while(iterator.hasNext()){
            HashMap<String,String> resultMap=new HashMap<>();
            Map.Entry pair=(Map.Entry)iterator.next();
            resultMap.put("First Line",pair.getKey().toString());
            resultMap.put("Second Line",pair.getValue().toString());
            faqs.add(resultMap);
        }

        lvFaq.setAdapter(adapter);

    }
}
