/*
 * Copyright © 2019, 2020, 2021, 2022, 2023 Peter Doornbosch
 *
 * This file is part of Kwik, an implementation of the QUIC protocol in Java.
 *
 * Kwik is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Kwik is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.luminis.quic;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;


class VersionTest {

    @Test
    void testParseDraft17Version() throws UnknownVersionException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0xff, 0x00, 0x00, 0x11 });
        int rawVersion = buffer.getInt();
        Version version = Version.parse(rawVersion);
        assertThat(version).isEqualTo(Version.IETF_draft_17);
    }

    @Test
    void testDraft17IsAtLeastDraft16() {
        assertThat(Version.IETF_draft_17.atLeast(Version.IETF_draft_16)).isEqualTo(true);
    }

    @Test
    void testParseDraft18Version() throws UnknownVersionException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0xff, 0x00, 0x00, 0x12 });
        int rawVersion = buffer.getInt();
        Version version = Version.parse(rawVersion);
        assertThat(version).isEqualTo(Version.IETF_draft_18);
    }

    @Test
    void testDraft18IsAtLeastDraft17() {
        assertThat(Version.IETF_draft_18.atLeast(Version.IETF_draft_17)).isEqualTo(true);
    }

    @Test
    void testParseDraft19Version() throws UnknownVersionException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0xff, 0x00, 0x00, 0x13 });
        int rawVersion = buffer.getInt();
        Version version = Version.parse(rawVersion);
        assertThat(version).isEqualTo(Version.IETF_draft_19);
    }

    @Test
    void testParseDraft20Version() throws UnknownVersionException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0xff, 0x00, 0x00, 0x14 });
        int rawVersion = buffer.getInt();
        Version version = Version.parse(rawVersion);
        assertThat(version).isEqualTo(Version.IETF_draft_20);
    }

    @Test
    void testDraft19IsAtLeastDraft17() {
        assertThat(Version.IETF_draft_19.atLeast(Version.IETF_draft_17)).isEqualTo(true);
    }

    @Test
    void testDraft18BeforeDraft19() {
        assertThat(Version.IETF_draft_18.before(Version.IETF_draft_19)).isEqualTo(true);
    }

    @Test
    void testV1IsAtLeastDraft33() {
        assertThat(Version.QUIC_version_1.atLeast(Version.IETF_draft_33)).isTrue();
    }

    @Test
    void testDraft33IsNotAtLeastV1() {
        assertThat(Version.IETF_draft_33.atLeast(Version.QUIC_version_1)).isFalse();
    }

    @Test
    void testV1IsNotBeforeDraft33() {
        assertThat(Version.QUIC_version_1.before(Version.IETF_draft_33)).isFalse();
    }

    @Test
    void testDraft33IsBeforeV1() {
            assertThat(Version.IETF_draft_33.before(Version.QUIC_version_1)).isTrue();
    }

    @Test
    void testDraft19BeforeDraft20() {
        assertThat(Version.IETF_draft_19.before(Version.IETF_draft_20)).isEqualTo(true);
    }

    @Test
    void testV1IsBeforeV2() {
        assertThat(Version.QUIC_version_1.before(Version.QUIC_version_2)).isTrue();
    }

    @Test
    void testParseDraft22Version() throws UnknownVersionException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0xff, 0x00, 0x00, 0x16 });
        int rawVersion = buffer.getInt();
        Version version = Version.parse(rawVersion);
        assertThat(version).isEqualTo(Version.IETF_draft_22);
    }

    @Test
    void testParseDraft29Version() throws UnknownVersionException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[] { (byte) 0xff, 0x00, 0x00, 0x1d });
        int rawVersion = buffer.getInt();
        Version version = Version.parse(rawVersion);
        assertThat(version).isEqualTo(Version.IETF_draft_29);
    }

    @Test
    void testGetDraftSuffix() {
        assertThat(Version.IETF_draft_29.getDraftVersion()).isEqualTo("29");
    }

    @Test
    void testDraft32ToString() {
        assertThat(Version.IETF_draft_32.toString()).isEqualTo("draft-32");
    }

    @Test
    void testUnknownVersionToString() {
        assertThat(new Version(0x9afa5aba).toString()).isEqualTo("v-9afa5aba");
    }

    @Test
    void testQuicVersion1ToString() {
        assertThat(Version.QUIC_version_1.toString()).isEqualTo("v1");
    }

    @Test
    void testQuicVersion2ToString() {
        assertThat(Version.QUIC_version_2.toString()).isEqualTo("v2");
    }

}
