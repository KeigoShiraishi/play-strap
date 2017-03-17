package filters

import javax.inject.Inject

import play.api.http.HttpFilters
import play.api.{Environment, Mode}
import play.filters.csrf.CSRFFilter
import play.filters.gzip.GzipFilter
import play.filters.headers.SecurityHeadersFilter

class CustomFilters @Inject()(env: Environment,
                              log: CustomLogFilter,
                              csrf: CSRFFilter,
                              httpsfilter: HTTPSRedirectFilter,
                              secure: SecurityHeadersFilter,
                              gzipFilter: GzipFilter) extends HttpFilters {

  override val filters = {
    (env.mode == Mode.Dev) match {
      case true  => Seq(log, csrf, gzipFilter, secure)
      case false => Seq(log, csrf, gzipFilter, secure)
    }
  }


}