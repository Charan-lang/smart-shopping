package com.example.rabiy.styleomega14.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Adapters.ColorAdapter;
import com.example.rabiy.styleomega14.Adapters.InquiriesAdapter;
import com.example.rabiy.styleomega14.Adapters.SizeAdapter;
import com.example.rabiy.styleomega14.Models.Cart;
import com.example.rabiy.styleomega14.Models.CartItem;
import com.example.rabiy.styleomega14.Models.Color;
import com.example.rabiy.styleomega14.Models.Inquiry;
import com.example.rabiy.styleomega14.Models.Item;
import com.example.rabiy.styleomega14.Models.Review;
import com.example.rabiy.styleomega14.Models.Size;
import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.Models.WishlistItem;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {

    private ArrayList<Color> mColorList;
    private ColorAdapter mColorAdapter;
    private ArrayList<Size> mSizeList;
    private SizeAdapter mSizeAdapter;
    private TextView tvPrice;
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvDescription;
    private Button btnAddToCart;
    private ImageButton btnAddToWishlist;
    private Button btnSendInquiries;
    private EditText edittxtInquiry;
    private RecyclerView rvPrevInquiries;
    private ListView lvReviews;


    int code;
    String img;
    String clickedSizeName;
    String clickedColorName;
    //List<Cart> lstCarts;
    List<CartItem> lstCartItems;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Product Details");
        setSupportActionBar(toolbar);

        ivImage=(ImageView)findViewById(R.id.item_image);
        tvName=(TextView)findViewById(R.id.item_name_tv);
        tvPrice=(TextView)findViewById(R.id.item_price_tv);
        tvDescription=(TextView)findViewById(R.id.tv_item_description);



        //Receive Data
        Intent intent=getIntent();
        code=intent.getExtras().getInt("Code");
        img=intent.getExtras().getString("Image");
        String name=intent.getExtras().getString("ItemName");
        String Price=intent.getExtras().getString("Price");
        String description=intent.getExtras().getString("Description");

        //Setting Values
        Picasso.get().load(img).into(ivImage);
        tvName.setText(name);
        tvPrice.setText(Price);
        tvDescription.setText(description);

        initList();

        Spinner spinnerColors=findViewById(R.id.spinner_color);

        mColorAdapter=new ColorAdapter(this,mColorList);
        spinnerColors.setAdapter(mColorAdapter);

        spinnerColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Color clickedColor= (Color) parent.getItemAtPosition(position);
                clickedColorName=clickedColor.getColor();
                Toast.makeText(ItemDetailsActivity.this,clickedColorName+" selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ItemDetailsActivity.this,"Select a Color",Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinnerSizes=findViewById(R.id.spinner_size);

        mSizeAdapter=new SizeAdapter(this,mSizeList);
        spinnerSizes.setAdapter(mSizeAdapter);

        spinnerSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Size clickedSize= (Size) parent.getItemAtPosition(position);
                clickedSizeName=clickedSize.getSize();
                Toast.makeText(ItemDetailsActivity.this,clickedSizeName+" selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ItemDetailsActivity.this,"Select a Size",Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToCart=findViewById(R.id.add_to_cart_btn);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lstCartItems=new ArrayList<>();
                String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                List<User> users=User.listAll(User.class);

                for (User user:users) {
                    if (user.getEmail().equals(userEmail)) {
                        if(user.getCart()!=null){
                            if(user.getCart().getStatus().equals("purchased")){

                                Cart cart=new Cart("pending",user);
                                cart.save();
                                List<Item> items=Item.findWithQuery(Item.class,
                                        "Select * from Item where code = ? ",Integer.toString(code));
                                for(Item item:items){
                                        if(item.getQtyInStock()>0){
                                            CartItem cartItem=new CartItem(1,item,cart
                                                    ,clickedSizeName,clickedColorName);
                                            lstCartItems.add(cartItem);
                                            cartItem.save();

                                            Long itemID = item.getId();
                                            Item updateItem = Item.findById(Item.class, itemID);
                                            updateItem.setQtyInStock(item.getQtyInStock()-1);
                                            updateItem.save();
                                            Toast.makeText(ItemDetailsActivity.this,
                                                    "Item Successfully Added to Cart",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(ItemDetailsActivity.this,
                                                    "Sorry This Item Is Sold Out!",Toast.LENGTH_SHORT).show();
                                        }
                                }
                                user.setCart(cart);
                                user.save();
                            }else{
                                Cart cart=user.getCart();
                                List<Item> items=Item.findWithQuery(Item.class,
                                        "Select * from Item where code = ? ",Integer.toString(code));
                                for(Item item:items){

                                        if(item.getQtyInStock()>0){
                                            CartItem cartItem=new CartItem(1,item,cart,
                                                    clickedSizeName,clickedColorName);
                                            lstCartItems.add(cartItem);
                                            cartItem.save();
                                            Long itemID = item.getId();
                                            Item updateItem = Item.findById(Item.class, itemID);
                                            updateItem.setQtyInStock(item.getQtyInStock()-1);
                                            updateItem.save();
                                            Toast.makeText(ItemDetailsActivity.this,
                                                    "Item Successfully Added to Cart",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(ItemDetailsActivity.this,
                                                    "Sorry This Item Is Sold Out!",Toast.LENGTH_SHORT).show();
                                        }
                                }
                                user.save();
                            }
                        }else{
                            Cart cart=new Cart("pending",user);
                            cart.save();

                            List<Item> items=Item.findWithQuery(Item.class,
                                    "Select * from Item where code = ? ",Integer.toString(code));

                            for(Item item:items){
                                    if(item.getQtyInStock()>0){
                                        CartItem cartItem=new CartItem(1,item,cart,clickedSizeName,clickedColorName);
                                        lstCartItems.add(cartItem);
                                        cartItem.save();
                                        Long itemID = item.getId();
                                        Item updateItem = Item.findById(Item.class, itemID);
                                        updateItem.setQtyInStock(item.getQtyInStock()-1);
                                        updateItem.save();
                                        Toast.makeText(ItemDetailsActivity.this,
                                                "Item Successfully Added to Cart",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ItemDetailsActivity.this,
                                                "Sorry This Item Is Sold Out!",Toast.LENGTH_SHORT).show();
                                    }
                            }
                            user.setCart(cart);
                            user.save();
                        }
                    }
                }
            }
        });

        btnAddToWishlist=findViewById(R.id.add_to_wishlist_btn);
        btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                List<User> users=User.listAll(User.class);
                for (User user:users) {
                    if (user.getEmail().equals(userEmail)) {
                        List<Item> items = Item.findWithQuery(Item.class,
                                "Select * from Item where code = ? ",Integer.toString(code));
                        for (Item item : items) {
                                WishlistItem wishlistItem = new WishlistItem( user, item);
                                wishlistItem.save();
                        }
                    }
                }
                btnAddToWishlist.setImageResource(R.drawable.heartpink);
                Toast.makeText(ItemDetailsActivity.this,
                        "Product Added to Wishlist",Toast.LENGTH_SHORT).show();
            }
        });

        lvReviews=findViewById(R.id.list_view_reviews);
        List<Review> reviews=Review.listAll(Review.class);
        int i=0;
        if(reviews.size()>0){
            String[] arr=new String[reviews.size()];
            if(reviews.size()>0) {
                for (Review review : reviews) {
                    Item reviewedItem = review.getItem();
                    if (reviewedItem.getCode() == code) {
                        String reviewUser=review.getUser().getfName()+" "
                                +review.getUser().getlName()+" : ";
                        String reviewContent=review.getReview();
                        arr[i]=reviewUser+"\n"+reviewContent;
                        i++;
                    }
                }
            }
            if(arr[0]!=null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, arr);
                lvReviews.setAdapter(adapter);
            }
        }else {
            String[] noReviews=new String[1];
            noReviews[0]="No reviews have been made about this product yet";
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, noReviews);
            lvReviews.setAdapter(adapter);
        }


        edittxtInquiry=findViewById(R.id.edit_txt_inquiry);
        btnSendInquiries=findViewById(R.id.send_inquiry_button);

        btnSendInquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inquiry=edittxtInquiry.getText().toString();

                String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                List<User> users=User.listAll(User.class);
                for (User user:users) {
                    if (user.getEmail().equals(userEmail)) {
                        List<Item> items=Item.listAll(Item.class);
                        for(Item item:items) {
                            if (item.getCode() == code) {
                                Inquiry inquiry1 = new Inquiry(inquiry, user, item);
                                inquiry1.save();
                                Toast.makeText(ItemDetailsActivity.this,
                                        "Your Inquiry Has Been Sent",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });


        rvPrevInquiries=findViewById(R.id.recycler_view_inquiries);
        List<Inquiry> itemInquiries=new ArrayList<>();
        List<Inquiry> inquiries=Inquiry.listAll(Inquiry.class);
        if(inquiries.size()>0) {
            Long inquiryID = inquiries.get(0).getId();
            Inquiry iReply = Inquiry.findById(Inquiry.class, inquiryID);
            iReply.setReply("Yes. We have Matte versions of this coat");
            iReply.save();

                for (Inquiry inquiry : inquiries) {
                    Item inquiredItem = inquiry.getItem();
                    if (inquiredItem.getCode() == code) {
                        itemInquiries.add(inquiry);
                    }
                }
        }
        InquiriesAdapter inquiriesAdapter=new InquiriesAdapter(this,itemInquiries);
        rvPrevInquiries.setLayoutManager(new LinearLayoutManager(this));
        rvPrevInquiries.setAdapter(inquiriesAdapter);
        rvPrevInquiries.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.share_button:
                Intent myIntent=new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/html");
                String shareBody=img;
                String shareSub="Check this Clothing Item I found At StyleOmega!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Share Using..."));
        }
        return super.onOptionsItemSelected(item);
    }


    private void initList() {
        mColorList=new ArrayList<>();
        mColorList.add(new Color("WHITE"));
        mColorList.add(new Color("BLACK"));
        mColorList.add(new Color("GRAY"));
        mColorList.add(new Color("RED"));

        mSizeList=new ArrayList<>();
        mSizeList.add(new Size("XS"));
        mSizeList.add(new Size("S"));
        mSizeList.add(new Size("M"));
        mSizeList.add(new Size("L"));
        mSizeList.add(new Size("XL"));
    }
}
