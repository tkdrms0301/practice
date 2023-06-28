import json
import hashlib
import datetime
# import logging
import feedparser

# import azure.functions as func

RSS_FEED_URL = "http://feeds.bbci.co.uk/news/england/london/rss.xml"


def get_feed():
    feed = feedparser.parse(RSS_FEED_URL)
    print("****")
    print(feed)
    print("****")
    # getting lists of blog entries via .entries
    posts = feed.entries[:2]  # 2 entries

    retdocs = []
    # iterating over individual posts
    for post in posts:
        temp = dict()

        # if any post doesn't have information then throw error.

        # temp["title"] = post.title
        # temp["link"] = post.link
        # temp["author"] = post.author
        # temp["time_published"] = post.published
        # temp["tags"] = [tag.term for tag in post.tags]
        # temp["authors"] = [author.name for author in post.authors]
        # temp["summary"] = post.summary
        # idhash = hashlib.sha1( post.link).encode('utf-8').hexdigest()
        # idhash = hashlib.sha1( (post.link).encode('utf-8')).hexdigest()
        retdoc = {
            # "id": idhash,
            "title": post.title,
            "summary": post.summary,
            "pubDate": post.published  # date
        }
        retdocs.append(retdoc)
    return retdocs


def main():
    try:
        # Get feeds
        outdata = {}
        outdata['items'] = get_feed()
        print(outdata)  # for debug

    except Exception as e:
        print('Error:')
        print(e)


if __name__ == "__main__":
    main()
