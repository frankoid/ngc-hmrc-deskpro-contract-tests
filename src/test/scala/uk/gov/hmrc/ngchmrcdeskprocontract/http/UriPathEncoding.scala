package uk.gov.hmrc.ngchmrcdeskprocontract.http

import java.nio.charset.StandardCharsets

import play.utils.UriEncoding

object UriPathEncoding {

  def encodePathSegments(pathSegments: String*): String =
    pathSegments.map(encodePathSegment).mkString("/", "/", "")

  def encodePathSegment(pathSegment: String): String =
    UriEncoding.encodePathSegment(pathSegment, StandardCharsets.UTF_8.name)
}