package filters

import javax.inject.Inject

import akka.stream.Materializer
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class HTTPSRedirectFilter @Inject()(implicit val mat: Materializer,
                                    ec: ExecutionContext) extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {

    requestHeader.headers.get("x-forwarded-proto") match {
      case Some(header) => {
        if ("https" == header) {
          nextFilter(requestHeader).map(x => x.withHeaders("Strict-Transport-Security" -> "max-age=3600; includeSubDomains"))
        } else {
          Future.successful(Results.Redirect("https://" + requestHeader.host + requestHeader.uri, 301))
        }
      }
      case None         =>
        (!requestHeader.secure) match {
          case true  => Future.successful(Results.MovedPermanently("https://" + requestHeader.host + requestHeader.uri))
          case false => nextFilter(requestHeader).map(x => x.withHeaders("Strict-Transport-Security" -> "max-age=3600; includeSubDomains"))
        }
      //        nextFilter(requestHeader)
    }
  }

}