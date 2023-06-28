import hashlib
import feedparser
import datetime
import logging
import json

import azure.functions as func

RSS_FEED_URL = "http://feeds.bbci.co.uk/news/world/asia/rss.xml"


def get_feed():
    feed = feedparser.parse(RSS_FEED_URL)
    # getting lists of blog entries via .entries
    posts = feed.entries[:5]

    retdocs = []
    # iterating over individual posts
    for post in posts:
        # if any post doesn't have information then throw error.

        # temp["title"] = post.title
        # temp["link"] = post.link
        # temp["author"] = post.author
        # temp["time_published"] = post.published
        # temp["tags"] = [tag.term for tag in post.tags]
        # temp["authors"] = [author.name for author in post.authors]
        # temp["summary"] = post.summary
        # idhash = hashlib.sha1( post.link).encode('utf-8').hexdigest()
        idhash = hashlib.sha1((post.link).encode('utf-8')).hexdigest()
        retdoc = {
            "id": idhash,
            "title": post.title,
            "pubDate": post.published  # date
        }
        retdocs.append(retdoc)
    return retdocs


def main(mytimer: func.TimerRequest, outdoc: func.Out[func.Document]):
    utc_timestamp = datetime.datetime.utcnow().replace(
        tzinfo=datetime.timezone.utc).isoformat()
    if mytimer.past_due:
        logging.info('The timer is past due!')
    logging.info('Python timer trigger function ran at %s', utc_timestamp)

    try:
        # Get Blog feeds
        outdata = {}
        outdata['items'] = get_feed()
        logging.info(outdata)  # for debug

        # Store output data using Cosmos DB output binding
        outdoc.set(func.Document.from_json(json.dumps(outdata)))
    except Exception as e:
        logging.error('Error:')
        logging.error(e)

# def main():
#     try:
#         # Get feeds
#         outdata = {}
#         outdata['items'] = get_feed()
#         print(outdata)  # for debug

#     except Exception as e:
#         print('Error:')
#         print(e)


if __name__ == "__main__":
    main()
