package com.example.news_app.Adapter

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import com.example.news_app.Dao.NewsDao
import com.example.news_app.R
import com.squareup.picasso.Picasso


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    var mNewsData: List<NewsDao.Article>? = null
    var mContext: Context? = null

    constructor(context: Context, data: List<NewsDao.Article>?) {
        this.mContext = context
        this.mNewsData = data
    }


    override fun getItemCount(): Int {
        return mNewsData!!.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setScaleAnimation(holder.itemView)

        val newsData: NewsDao.Article = mNewsData!!.get(position)

        Picasso.with(mContext)
                .load(newsData.urlToImage)
                .into(holder.ivPreview)

        holder.tvTitle.text = newsData.title
        holder.tvDesc.text = newsData.description
        holder.tvDate.text = newsData.publishedAt
        holder.tvSrc.text = newsData.source.name
        holder.cvLayout.setOnClickListener {


            val url = newsData.url
            val builder = CustomTabsIntent.Builder()
            builder.setStartAnimations(mContext!!, R.anim.slide_right,R.anim.slide_right)
            builder.setExitAnimations(mContext!!, R.anim.slide_left, R.anim.slide_left)
            val customTabsIntent = builder.build()
            builder.setToolbarColor(ContextCompat.getColor(mContext!!, R.color.colorPrimary))
            customTabsIntent.launchUrl(mContext!!, Uri.parse(url))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false)

        return ViewHolder(inflater)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivPreview = itemView?.findViewById<View>(R.id.ivPreview) as ImageView
        var cvLayout = itemView?.findViewById<View>(R.id.cvLayout) as CardView
        var tvTitle = itemView?.findViewById<View>(R.id.tvTitle) as TextView
        var tvDesc = itemView?.findViewById<View>(R.id.tvDesc) as TextView
        var tvDate = itemView?.findViewById<View>(R.id.tvDate) as TextView
        var tvSrc = itemView?.findViewById<View>(R.id.tvSrc) as TextView
    }

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 500
        view.startAnimation(anim)
    }
}